package jvm.interpreter;

/**
 * @author 22454
 */
public class CodeReader {
    private byte[] code;
    private int pc;

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public int getPc() {
        return pc;
    }

    public byte readByte() {
        return code[pc++];
    }

    public short readShort() {
        int high = (readByte() & 0xff) << 8;
        int low = (readByte() & 0xff);
        return (short) (high | low);
    }

    public int readInteger() {
        int highFrom15To8 = readByte() << 24;
        int highFrom7To0 = readByte() << 16;
        int lowFrom15To8 = readByte() << 8;
        int lowFrom7To0 = readByte();
        return highFrom15To8 | highFrom7To0 | lowFrom15To8 | lowFrom7To0;
    }

    public int[] readIntegerByCount(int count) {
        int[] integers = new int[count];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = readInteger();
        }
        return integers;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            readByte();
        }
    }

}
