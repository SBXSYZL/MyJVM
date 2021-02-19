package com.myJvm.jvm.interpreter;

import com.myJvm.jvm.interpreter.instructions.Instruction;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
public class InstructionManagerCenter {
    private static final ConcurrentHashMap<String, Instruction> INSTRUCTION_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    public static void addInstruction(Class<?> cls) {
        try {
            Instruction instruction = (Instruction) cls.newInstance();
            String instructionName = instruction.getClass().getSimpleName().toLowerCase();
            INSTRUCTION_CONCURRENT_HASH_MAP.put(instructionName, instruction);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Instruction getInstruction(String instructionName) {
        return INSTRUCTION_CONCURRENT_HASH_MAP.get(instructionName.toLowerCase());
    }

    public static void showInstructions() {
        System.out.println("********************ALL Registered Instruction***************************");
        INSTRUCTION_CONCURRENT_HASH_MAP.forEach((k, v) -> {
            System.out.println(k);
        });
        System.out.println("*************************************************************************");
    }
}
