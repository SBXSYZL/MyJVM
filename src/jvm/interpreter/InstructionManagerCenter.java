package jvm.interpreter;

import jvm.BeanCenter.MyAutoWired;
import jvm.BeanCenter.MyBean;
import jvm.interpreter.instructions.InstructionExecutorImpl.*;
import jvm.runtimeDataArea.threadDependent.StackFrame;

/**
 * @author 22454
 */
@MyBean
public class InstructionManagerCenter {
    @MyAutoWired
    private ConstantInstructionExecutor constantInstructionExecutor;
    @MyAutoWired
    private LoadInstructionExecutor loadInstructionExecutor;
    @MyAutoWired
    private StoreInstructionExecutor storeInstructionExecutor;
    @MyAutoWired
    private StackInstructionExecutor stackInstructionExecutor;
    @MyAutoWired
    private MathInstructionExecutor mathInstructionExecutor;
    @MyAutoWired
    private ConversionInstructionExecutor conversionInstructionExecutor;
    @MyAutoWired
    private CompareInstructionExecutor compareInstructionExecutor;
    @MyAutoWired
    private ControlInstructionExecutor controlInstructionExecutor;
    @MyAutoWired
    private QuoteInstructionExecutor quoteInstructionExecutor;
    @MyAutoWired
    private ExtensionInstructionExecutor extensionInstructionExecutor;
    @MyAutoWired
    private ReservedInstructionExecutor reservedInstructionExecutor;

    /**
     * 运行指令
     */
    public void invokeInstruction(int operatorCode, CodeReader codeReader, StackFrame stackTopFrame) {

        //找到对应指令，如果必要，获取操作数
        if (constantInstructionExecutor.isConstantInstruction(operatorCode)) {
            constantInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (loadInstructionExecutor.isLoadInstruction(operatorCode)) {
            loadInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (storeInstructionExecutor.isStoreInstruction(operatorCode)) {
            storeInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (stackInstructionExecutor.isStackInstruction(operatorCode)) {
            stackInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (mathInstructionExecutor.isMathInstruction(operatorCode)) {
            mathInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (conversionInstructionExecutor.isConversionInstruction(operatorCode)) {
            conversionInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (compareInstructionExecutor.isCompareInstruction(operatorCode)) {
            compareInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (controlInstructionExecutor.isControlInstruction(operatorCode)) {
            controlInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (quoteInstructionExecutor.isQuoteInstruction(operatorCode)) {
            quoteInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (extensionInstructionExecutor.isExtensionInstruction(operatorCode)) {
            extensionInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else if (reservedInstructionExecutor.isReservedInstruction(operatorCode)) {
            reservedInstructionExecutor.execute(operatorCode, stackTopFrame, codeReader);
        } else {
            throw new RuntimeException("Error Instruction....");
        }

    }

}
