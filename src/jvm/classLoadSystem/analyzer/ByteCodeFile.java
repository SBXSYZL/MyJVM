package jvm.classLoadSystem.analyzer;

import java.math.BigInteger;

/**
 * @author 22454
 * u1 byte
 * u2 int
 * u4 long
 */
public class ByteCodeFile {
    /**
     * 字节码数组
     */
    private byte[] bytecode;

    public ByteCodeFile(byte[] bytecode) {
        this.bytecode = bytecode;
    }

    /**
     * 获取指定数量的 byte
     *
     * @param cnt 数量
     * @return byte 数组
     */
    private byte[] getBytes(int cnt) {
        byte[] bytes = new byte[cnt];
        System.arraycopy(bytecode, 0, bytes, 0, cnt);
        System.arraycopy(bytecode, cnt, bytecode, 0, bytecode.length - cnt);
        return bytes;
    }

    /**
     * 根据数量读取 cnt 个 byte（外部访问接口）
     */
    public byte[] getByteByCnt(int cnt) {
        return getBytes(cnt);
    }

    /**
     * U1
     */
    public int readOneUint() {
        return byteArrayToInteger(getBytes(1));
    }

    /**
     * U2
     */
    public int readTwoUint() {
        byte[] bytes = getBytes(2);
        return byteArrayToInteger(bytes);
    }

    /**
     * U4
     */
    public long readFourUint() {
        byte[] bytes = getBytes(4);
        return byteArrayToLong(bytes);
    }

    /**
     * 读取一个 Integer
     */
    public int readInteger() {
        byte[] bytes = getBytes(4);
        return new BigInteger(bytes).intValue();
    }

    /**
     * 读取一个 Double
     */
    public double readDouble() {
        byte[] bytes = getBytes(8);
        return new BigInteger(1, bytes).doubleValue();
    }

    /**
     * 读取一个 Float
     */
    public float readFloat() {
        byte[] bytes = getBytes(4);
        return new BigInteger(1, bytes).floatValue();
    }


    /**
     * 读取一个 Long
     */
    public long readLong() {
        byte[] bytes = getBytes(8);
        return new BigInteger(1, bytes).longValue();
    }

    /**
     * 一个 byte[] 转换成一个 Integer
     */
    private int byteArrayToInteger(byte[] value) {
        String valStr = new BigInteger(1, value).toString(16);
        return Integer.parseInt(valStr, 16);
    }

    /**
     * 一个 byte[] 转换成一个 Long
     */
    private long byteArrayToLong(byte[] value) {
        String valStr = new BigInteger(1, value).toString(16);
        return Long.parseLong(valStr, 16);
    }
}
