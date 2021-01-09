package jvm.interpreter;

import jvm.BeanCenter.MyBeanCenter;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.runtimeDataArea.MyThread;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.shared.heap.info.dependence.StringCache;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

/**
 * 解释器
 *
 * @author 22454
 */
public class Interpreter {

    private InstructionManagerCenter instructionManagerCenter = (InstructionManagerCenter) MyBeanCenter.getBean(InstructionManagerCenter.class);


    public Interpreter(MyMethod method, String args) {
        try {
            //创建守护线程
            MyThread thread = new MyThread();
            //创建开始第一个栈帧
            StackFrame startFrame = new StackFrame(thread, method);
            //如果参数不为空，解析参数，并放进栈帧
            if (null != args) {
                //参数数组对象
                MyObject arg = parseArgs(method.getClazz().getClassLoader(), args);
                startFrame.getLocalVariableTable().putRef(0, arg);
            }
            //守护线程放入第一个栈帧
            thread.pushStackFrame(startFrame);
            //开始解释
            this.run(thread);

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
            MyLog.command(operatorCodeHex);
            //执行指令
            instructionManagerCenter.invokeInstruction(operatorCode, codeReader, stackTopFrame);
            //设置下一个的pc
            stackTopFrame.setNextPc(codeReader.getPc());
        } while (!thread.stackEmpty());

    }
}
