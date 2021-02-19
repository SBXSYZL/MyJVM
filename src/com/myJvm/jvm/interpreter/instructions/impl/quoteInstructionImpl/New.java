package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.interpreter.instructions.logic.ClassInitLogic;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyClassRef;
import com.myJvm.log.MyLog;

@MyInstruction
public class New extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("new");
        try {
            int index = this.value;
            RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
            MyClassRef classRef = (MyClassRef) runtimeConstantPool.getConstants(index);
            MyClass myClass = classRef.resolvedClass();
            if (!myClass.isInitStarted()) {
                frame.revertNextPc();
                ClassInitLogic.initClass(frame.getThread(), myClass);
                return;
            }
            if (myClass.isInterface() || myClass.isAbstract()) {
                throw new InstantiationError();
            }
            MyObject object = myClass.newObject();
            frame.getOperandStack().pushRef(object);
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error(e.getMessage());
        }

    }
}
