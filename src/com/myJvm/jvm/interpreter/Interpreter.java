package com.myJvm.jvm.interpreter;

import com.myJvm.jvm.interpreter.instructions.Instruction;
import com.myJvm.jvm.interpreter.instructions.InstructionEnum;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithOperands;
import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.MyThread;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

/**
 * 解释器
 *
 * @author 22454
 */
public class Interpreter {
    private MyMethod mainMethod;
    private String args;
    private MyThread daemonThread;

    public Interpreter(MyMethod method, String args) {
        mainMethod = method;
        this.args = args;
        init();
    }

    public void start() {
        //开始解释
        this.run(daemonThread);
    }

    private void init() {
        try {
            //创建守护线程
            daemonThread = new MyThread();
            //创建开始第一个栈帧
            StackFrame startFrame = new StackFrame(daemonThread, mainMethod);
            //如果参数不为空，解析参数，并放进栈帧
            if (null != args) {
                //参数数组对象
                MyObject arg = parseArgs(mainMethod.getClazz().getClassLoader(), args);
                startFrame.getLocalVariableTable().putRef(0, arg);
            }
            //守护线程放入第一个栈帧
            daemonThread.pushStackFrame(startFrame);


        } catch (Exception e) {
            MyLog.error("Failed To Create Interpreter.");
            e.printStackTrace();
        }
    }

    private MyObject parseArgs(MyClassLoader classLoader, String argStr) {
        String[] args = argStr.split(" ");
        try {
            MyClass stringClass = classLoader.findClass("java/lang/String");
            MyObject array = stringClass.getClassLoader()
                    //找到数组类
                    .findClass(createArrayName(stringClass.getClassName()))
                    //数组类创建数组
                    .createArray(args.length);
            MyObject[] refs = array.getRefs();
            for (int i = 0; i < refs.length; i++) {
                refs[i] = StringCache.putString(classLoader, args[i]);
            }
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createArrayName(String className) {
        String result = "[";
        if (className.getBytes()[0] != '[') {
            String str = MyClassLoader.PRIMITIVE_TYPE.get(className);
            if (str != null) {
                result += str;

            } else {
                result += ("L" + className + ";");
            }
            return result;
        }
        return className;
    }

    private void run(MyThread thread) {
        CodeReader codeReader = new CodeReader();
//        InstructionManagerCenter.showInstructions();
        do {
            //获取栈顶帧
            StackFrame stackTopFrame = thread.getStackTopFrame();
            String className = stackTopFrame.getMethod().getClazz().getClassName();
            String currentMethodName = stackTopFrame.getMethod().getName();
            MyLog.debug(className + " invoke " + currentMethodName);
            //获取下一个 PC
            int nextPc = stackTopFrame.getNextPc();
            //给线程设置新的 PC
            thread.setNextPc(nextPc);
            //取出当前栈帧的 code，交给 Code Reader 处理
            MyLog.command("current Pc : " + nextPc);
            codeReader.reset(stackTopFrame.getMethod().getCode(), nextPc);
            if (codeReader.emptyByteCode()) {
                MyLog.error(stackTopFrame.toString());
                MyLog.error(codeReader.toString());
            }
            //获取操作指令代码
            int operatorCode = Byte.toUnsignedInt(codeReader.readByte());
            String operatorCodeHex = Integer.toHexString(operatorCode & 0xff);
            if (operatorCodeHex.length() == 1) {
                operatorCodeHex = "0" + operatorCodeHex;
            }
            operatorCodeHex = "0x" + operatorCodeHex;
            MyLog.command("" + " : " + operatorCodeHex);
            //执行指令
            String instructionName;
            if (operatorCode >= 0xca) {
                instructionName = "invokeNative";
            } else {
                instructionName = InstructionEnum.findInstructionName(operatorCode);
            }

            Instruction instruction = InstructionManagerCenter.getInstruction(instructionName);
            if (instruction == null) {
                throw new RuntimeException("Unsupported instruction " + instructionName);
            }
            instruction.updateStackFrame(stackTopFrame, codeReader);
            if (instruction instanceof InstructionWithOperands) {
                ((InstructionWithOperands) instruction).readValue(codeReader);
            }
            //设置下一个的pc
            stackTopFrame.setNextPc(codeReader.getPc());
            instruction.exec();

        } while (!thread.stackEmpty());

    }
}
