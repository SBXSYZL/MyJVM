package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.shared.heap.info.dependence.StringCache;
import jvm.runtimeDataArea.shared.heap.info.ref.MyClassRef;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

/**
 * @author 22454
 */
public class ConstantInstructionExecutor implements InstructionExecutor {

    /**
     * 判断是不是常量相关类型指令
     *
     * @param operatorCode 操作码
     * @return 是否符合条件
     */
    public boolean isConstantInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.NOP && operatorCode <= InstructionEnum.LDC2_W;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.NOP:
                this.nop();
                break;
            case InstructionEnum.A_CONST_NULL:
                this.aConstNull(frame);
                break;
            case InstructionEnum.I_CONST_M_1:
                iConstM1(frame);
                break;
            case InstructionEnum.I_CONST_0:
                this.iConst0(frame);
                break;
            case InstructionEnum.I_CONST_1:
                this.iConst1(frame);
                break;
            case InstructionEnum.I_CONST_2:
                this.iConst2(frame);
                break;
            case InstructionEnum.I_CONST_3:
                this.iConst3(frame);
                break;
            case InstructionEnum.I_CONST_4:
                this.iConst4(frame);
                break;
            case InstructionEnum.I_CONST_5:
                this.iConst5(frame);
                break;
            case InstructionEnum.L_CONST_0:
                this.lConst0(frame);
                break;
            case InstructionEnum.L_CONST_1:
                this.lConst1(frame);
                break;
            case InstructionEnum.F_CONST_0:
                this.fConst0(frame);
                break;
            case InstructionEnum.F_CONST_1:
                this.fConst1(frame);
                break;
            case InstructionEnum.F_CONST_2:
                this.fConst2(frame);
                break;
            case InstructionEnum.D_CONST_0:
                this.dConst0(frame);
                break;
            case InstructionEnum.D_CONST_1:
                this.dConst1(frame);
                break;
            case InstructionEnum.BI_PUSH:
                this.biPush(frame, reader);
                break;
            case InstructionEnum.SI_PUSH:
                this.siPush(frame, reader);
                break;
            case InstructionEnum.LDC:
                this.ldc(frame, reader);
                break;
            case InstructionEnum.LDC_W:
                this.ldcW(frame, reader);
                break;
            case InstructionEnum.LDC2_W:
                this.ldc2W(frame, reader);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    /**
     * 啥也不做
     */
    private void nop() {
        MyLog.command("nop");
    }

    /**
     * 将 null 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void aConstNull(StackFrame frame) {
        MyLog.command("aconst_null");
        frame.getOperandStack().pushRef(null);
    }

    /**
     * 将 int 类型 -1 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iConstM1(StackFrame frame) {
        MyLog.command("iconst_m1");
        frame.getOperandStack().pushInteger(-1);
    }

    /**
     * 将 int 类型 0 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iConst0(StackFrame frame) {
        MyLog.command("iconst_0");
        frame.getOperandStack().pushInteger(0);
    }

    /**
     * 将 int 类型 1 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iConst1(StackFrame frame) {
        MyLog.command("iconst_1");
        frame.getOperandStack().pushInteger(1);
    }

    /**
     * 将 int 类型 2 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iConst2(StackFrame frame) {
        MyLog.command("iconst_2");
        frame.getOperandStack().pushInteger(2);
    }

    /**
     * 将 int 类型 3 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iConst3(StackFrame frame) {
        MyLog.command("iconst_3");
        frame.getOperandStack().pushInteger(3);
    }

    /**
     * 将 int 类型 4 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iConst4(StackFrame frame) {
        MyLog.command("iconst_4");
        frame.getOperandStack().pushInteger(4);
    }

    /**
     * 将 int 类型 5 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iConst5(StackFrame frame) {
        MyLog.command("iconst_5");
        frame.getOperandStack().pushInteger(5);
    }

    /**
     * 将 long 类型 0 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lConst0(StackFrame frame) {
        MyLog.command("lconst_0");
        frame.getOperandStack().pushLong(0L);
    }

    /**
     * 将 long 类型 1 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lConst1(StackFrame frame) {
        MyLog.command("lconst_1");
        frame.getOperandStack().pushLong(1L);
    }

    /**
     * 将 float 类型 0 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fConst0(StackFrame frame) {
        MyLog.command("fconst_0");
        frame.getOperandStack().pushFloat(0.0f);
    }

    /**
     * 将 float 类型 1.0f 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fConst1(StackFrame frame) {
        MyLog.command("fconst_1");
        frame.getOperandStack().pushFloat(1.0f);
    }

    /**
     * 将 float 类型 2.0f 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fConst2(StackFrame frame) {
        MyLog.command("fconst_2");
        frame.getOperandStack().pushFloat(2.0f);
    }

    /**
     * 将 double 类型 0.0d 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dConst0(StackFrame frame) {
        MyLog.command("dconst_0");
        frame.getOperandStack().pushDouble(0.0d);
    }

    /**
     * 将 double 类型 1.0f 推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dConst1(StackFrame frame) {
        MyLog.command("dconst_1");
        frame.getOperandStack().pushDouble(1.0d);
    }

    /**
     * 将单字节常量值（-128~127）推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void biPush(StackFrame frame, CodeReader reader) {
        MyLog.command("bipush");
        int val = reader.readByte();
        frame.getOperandStack().pushInteger(val);
    }

    /**
     * 将一个短整型常量值（-32 768~32 767）推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void siPush(StackFrame frame, CodeReader reader) {
        MyLog.command("sipush");
        int val = reader.readShort();
        frame.getOperandStack().pushInteger(val);
    }

    /**
     * 将 int\float\String 类型常量从常量池推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void ldc(StackFrame frame, CodeReader reader) {
        MyLog.command("ldc");
        int runtimeConstantIndex = reader.readByte();
        OperandStack operandStack = frame.getOperandStack();
        MyClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        Object constants = runtimeConstantPool.getConstants(runtimeConstantIndex);
        //如果是 Integer
        if (constants instanceof Integer) {
            operandStack.pushInteger((Integer) constants);
            return;
        }
        //如果是 Float
        else if (constants instanceof Float) {
            operandStack.pushFloat((Float) constants);
            return;
        }
        //如果是 String
        else if (constants instanceof String) {
            MyObject string = StringCache.putString(clazz.getClassLoader(), (String) constants);
            operandStack.pushRef(string);
            return;
        }
        //如果是 MyClassRef
        else if (constants instanceof MyClassRef) {
            MyClassRef classRef = (MyClassRef) constants;
            try {
                MyObject reflectClass = classRef.resolvedClass().getReflectClass();
                operandStack.pushRef(reflectClass);
            } catch (Exception e) {
                MyLog.error("Failed To Get Reflect Class");
                e.printStackTrace();
            }
            return;
        }
        throw new RuntimeException("Failed To Do Ldc Instruction " + constants.getClass().getName());

    }

    /**
     * 将 int\float\String 类型常量从常量池推送至栈顶（宽索引）
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void ldcW(StackFrame frame, CodeReader reader) {
        MyLog.command("ldcw");
        int runtimeConstantIndex = reader.readShort();
        OperandStack operandStack = frame.getOperandStack();
        MyClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        Object constants = runtimeConstantPool.getConstants(runtimeConstantIndex);
        if (constants instanceof Integer) {
            operandStack.pushInteger((Integer) constants);
            return;
        } else if (constants instanceof Float) {
            operandStack.pushFloat((Float) constants);
            return;
        } else if (constants instanceof String) {
            MyObject string = StringCache.putString(clazz.getClassLoader(), (String) constants);
            operandStack.pushRef(string);
            return;
        }
        throw new RuntimeException("Failed To DO Ldc_W Instruction " + constants.getClass().getName());
    }

    /**
     * 将 long\double 类型常量从常量池推送至栈顶（宽索引）
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void ldc2W(StackFrame frame, CodeReader reader) {
        MyLog.command("ldc2w");
        int runtimeConstantIndex = reader.readShort();
        OperandStack operandStack = frame.getOperandStack();
        MyClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        Object constants = runtimeConstantPool.getConstants(runtimeConstantIndex);
        if (constants instanceof Long) {
            operandStack.pushLong((Long) constants);
            return;
        } else if (constants instanceof Double) {
            operandStack.pushDouble((Double) constants);
            return;
        }
        throw new RuntimeException("Failed To Do Ldc2_W Instruction " + constants.getClass().getName());
    }

}
