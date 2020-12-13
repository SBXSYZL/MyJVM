package jvm.interpreter.instructions;

import log.MyLog;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
public final class InstructionEnum {
    public static final byte NOP = 0x00;
    public static final int A_CONST_NULL = 0x01;
    public static final int I_CONST_M_1 = 0x02;
    public static final int I_CONST_0 = 0x03;
    public static final int I_CONST_1 = 0x04;
    public static final int I_CONST_2 = 0x05;
    public static final int I_CONST_3 = 0x06;
    public static final int I_CONST_4 = 0x07;
    public static final int I_CONST_5 = 0x08;
    public static final int L_CONST_0 = 0x09;
    public static final int L_CONST_1 = 0x0a;
    public static final int F_CONST_0 = 0x0b;
    public static final int F_CONST_1 = 0x0c;
    public static final int F_CONST_2 = 0x0d;
    public static final int D_CONST_0 = 0x0e;
    public static final int D_CONST_1 = 0x0f;
    public static final int BI_PUSH = 0x10;
    public static final int SI_PUSH = 0x11;
    public static final int LDC = 0x12;
    public static final int LDC_W = 0x13;
    public static final int LDC2_W = 0x14;
    public static final int I_LOAD = 0x15;
    public static final int L_LOAD = 0x16;
    public static final int F_LOAD = 0x17;
    public static final int D_LOAD = 0x18;
    public static final int A_LOAD = 0x19;
    public static final int I_LOAD_0 = 0x1a;
    public static final int I_LOAD_1 = 0x1b;
    public static final int I_LOAD_2 = 0x1c;
    public static final int I_LOAD_3 = 0x1d;
    public static final int L_LOAD_0 = 0x1e;
    public static final int L_LOAD_1 = 0x1f;
    public static final int L_LOAD_2 = 0x20;
    public static final int L_LOAD_3 = 0x21;
    public static final int F_LOAD_0 = 0x22;
    public static final int F_LOAD_1 = 0x23;
    public static final int F_LOAD_2 = 0x24;
    public static final int F_LOAD_3 = 0x25;
    public static final int D_LOAD_0 = 0x26;
    public static final int D_LOAD_1 = 0x27;
    public static final int D_LOAD_2 = 0x28;
    public static final int D_LOAD_3 = 0x29;
    public static final int A_LOAD_0 = 0x2a;
    public static final int A_LOAD_1 = 0x2b;
    public static final int A_LOAD_2 = 0x2c;
    public static final int A_LOAD_3 = 0x2d;
    public static final int I_A_LOAD = 0x2e;
    public static final int L_A_LOAD = 0x2f;
    public static final int F_A_LOAD = 0x30;
    public static final int D_A_LOAD = 0x31;
    public static final int A_A_LOAD = 0x32;
    public static final int B_A_LOAD = 0x33;
    public static final int C_A_LOAD = 0x34;
    public static final int S_A_LOAD = 0x35;
    public static final int I_STORE = 0x36;
    public static final int L_STORE = 0x37;
    public static final int F_STORE = 0x38;
    public static final int D_STORE = 0x39;
    public static final int A_STORE = 0x3a;
    public static final int I_STORE_0 = 0x3b;
    public static final int I_STORE_1 = 0x3c;
    public static final int I_STORE_2 = 0x3d;
    public static final int I_STORE_3 = 0x3e;
    public static final int L_STORE_0 = 0x3f;
    public static final int L_STORE_1 = 0x40;
    public static final int L_STORE_2 = 0x41;
    public static final int L_STORE_3 = 0x42;
    public static final int F_STORE_0 = 0x43;
    public static final int F_STORE_1 = 0x44;
    public static final int F_STORE_2 = 0x45;
    public static final int F_STORE_3 = 0x46;
    public static final int D_STORE_0 = 0x47;
    public static final int D_STORE_1 = 0x48;
    public static final int D_STORE_2 = 0x49;
    public static final int D_STORE_3 = 0x4a;
    public static final int A_STORE_0 = 0x4b;
    public static final int A_STORE_1 = 0x4c;
    public static final int A_STORE_2 = 0x4d;
    public static final int A_STORE_3 = 0x4e;
    public static final int I_A_STORE = 0x4f;
    public static final int L_A_STORE = 0x50;
    public static final int F_A_STORE = 0x51;
    public static final int D_A_STORE = 0x52;
    public static final int A_A_STORE = 0x53;
    public static final int B_A_STORE = 0x54;
    public static final int C_A_STORE = 0x55;
    public static final int S_A_STORE = 0x56;
    public static final int POP = 0x57;
    public static final int POP_2 = 0x58;
    public static final int DUP = 0x59;
    public static final int DUP_X_1 = 0x5a;
    public static final int DUP_X_2 = 0x5b;
    public static final int DUP_2 = 0x5c;
    public static final int DUP_2_X_1 = 0x5d;

    public static final int DUP_2_X_2 = 0x5e;
    public static final int SWAP = 0x5f;
    public static final int I_ADD = 0x60;
    public static final int L_ADD = 0x61;
    public static final int F_ADD = 0x62;
    public static final int D_ADD = 0x63;
    public static final int I_SUB = 0x64;
    public static final int L_SUB = 0x65;
    public static final int F_SUB = 0x66;
    public static final int D_SUB = 0x67;
    public static final int I_MUL = 0x68;
    public static final int L_MUL = 0x69;
    public static final int F_MUL = 0x6a;
    public static final int D_MUL = 0x6b;
    public static final int I_DIV = 0x6c;
    public static final int L_DIV = 0x6d;
    public static final int F_DIV = 0x6e;
    public static final int D_DIV = 0x6f;
    public static final int I_REM = 0x70;
    public static final int L_REM = 0x71;
    public static final int F_REM = 0x72;
    public static final int D_REM = 0x73;
    public static final int I_NEG = 0x74;
    public static final int L_NEG = 0x75;
    public static final int F_NEG = 0x76;
    public static final int D_NEG = 0x77;
    public static final int I_SH_L = 0x78;
    public static final int L_SH_L = 0x79;
    public static final int I_SH_R = 0x7a;
    public static final int L_SH_R = 0x7b;
    public static final int I_U_SH_R = 0x7c;
    public static final int L_U_SH_R = 0x7d;
    public static final int I_AND = 0x7e;
    public static final int L_AND = 0x7f;
    public static final int I_OR = 0x80;
    public static final int L_OR = 0x81;
    public static final int I_XOR = 0x82;
    public static final int L_XOR = 0x83;
    public static final int I_INC = 0x84;
    public static final int I_2_L = 0x85;
    public static final int I_2_F = 0x86;
    public static final int I_2_D = 0x87;
    public static final int L_2_I = 0x88;
    public static final int L_2_F = 0x89;
    public static final int L_2_D = 0x8a;
    public static final int F_2_I = 0x8b;
    public static final int F_2_L = 0x8c;
    public static final int F_2_D = 0x8d;
    public static final int D_2_I = 0x8e;
    public static final int D_2_L = 0x8f;
    public static final int D_2_F = 0x90;
    public static final int I_2_B = 0x91;
    public static final int I_2_C = 0x92;
    public static final int I_2_S = 0x93;
    public static final int L_CMP = 0x94;
    public static final int F_CMP_L = 0x95;
    public static final int F_CMP_G = 0x96;
    public static final int D_CMP_L = 0x97;
    public static final int D_CMP_G = 0x98;
    public static final int IF_EQ = 0x99;
    public static final int IF_N_E = 0x9a;
    public static final int IF_L_T = 0x9b;
    public static final int IF_G_E = 0x9c;
    public static final int IF_G_T = 0x9d;
    public static final int IF_L_E = 0x9e;
    public static final int IF_I_CMP_EQ = 0x9f;
    public static final int IF_I_CMP_N_E = 0xa0;
    public static final int IF_I_CMP_L_T = 0xa1;
    public static final int IF_I_CMP_G_E = 0xa2;
    public static final int IF_I_CMP_G_T = 0xa3;
    public static final int IF_I_CMP_L_E = 0xa4;
    public static final int IF_A_CMP_EQ = 0xa5;
    public static final int IF_A_CMP_N_E = 0xa6;
    public static final int GOTO = 0xa7;
    public static final int JSR = 0xa8;
    public static final int RET = 0xa9;
    public static final int TABLE_SWITCH = 0xaa;
    public static final int LOOK_UP_SWITCH = 0xab;
    public static final int I_RETURN = 0xac;
    public static final int L_RETURN = 0xad;
    public static final int F_RETURN = 0xae;
    public static final int D_RETURN = 0xaf;
    public static final int A_RETURN = 0xb0;
    public static final int RETURN = 0xb1;
    public static final int GET_STATIC = 0xb2;
    public static final int PUT_STATIC = 0xb3;
    public static final int GET_FIELD = 0xb4;
    public static final int PUT_FIELD = 0xb5;
    public static final int INVOKE_VIRTUAL = 0xb6;
    public static final int INVOKE_SPECIAL = 0xb7;
    public static final int INVOKE_STATIC = 0xb8;
    public static final int INVOKE_INTERFACE = 0xb9;
    public static final int INVOKE_DYNAMIC = 0xba;
    public static final int NEW = 0xbb;
    public static final int NEW_ARRAY = 0xbc;
    public static final int A_NEW_ARRAY = 0xbd;
    public static final int ARRAY_LENGTH = 0xbe;
    public static final int A_THROW = 0xbf;
    public static final int CHECK_CAST = 0xc0;
    public static final int INSTANCE_OF = 0xc1;
    public static final int MONITOR_ENTER = 0xc2;
    public static final int MONITOR_EXIT = 0xc3;
    public static final int WIDE = 0xc4;
    public static final int MULTI_A_NEW_ARRAY = 0xc5;
    public static final int IF_NULL = 0xc6;
    public static final int IF_NON_NULL = 0xc7;
    public static final int GOTO_W = 0xc8;
    public static final int JSR_W = 0xc9;
    public static final int BREAK_POINT = 0xca;
    public static final int IMP_DEP_1 = 0xfe;
    public static final int IMP_DEP_2 = 0xff;

    //除了以上指令，其他字段不可以为public
    private static final InstructionEnum INSTANCE = new InstructionEnum();
    private static final ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

    //缓存 map 初始化
    static {
        Class<InstructionEnum> instructionEnumClass = InstructionEnum.class;
        Field[] declaredFields = instructionEnumClass.getFields();
        for (Field declaredField : declaredFields) {
            try {
                map.put(declaredField.getInt(INSTANCE), declaredField.getName().toLowerCase());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public static String findInstructionName(int instruction) throws Exception {
        instruction &= 0xff;

        String s = map.get(instruction);
        MyLog.debug(instruction + "    " + s);
        if (s == null) {
            throw new Exception("Find Instruction Error");
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println((1 << 8) - 1);
    }


}
