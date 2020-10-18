package exception;

/**
 * @author 22454
 */
public interface CommonError {
    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    public int getErrCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getErrMsg();

    /**
     * 设置错误信息
     *
     * @param msg 错误信息
     */
    public void setErrMsg(String msg);
}
