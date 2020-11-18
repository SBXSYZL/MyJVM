package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author 22454
 */
public class JvmException extends Exception implements CommonError {
    private final Exception exception;
    private final CommonError commonError;

    public JvmException(CommonError commonError) {
        this(null, commonError);
    }

    public JvmException(CommonError commonError, String msg) {
        this(null, commonError, msg);
    }

    public JvmException(Exception e, CommonError commonError) {
        this(e, commonError, commonError.getErrMsg());
    }

    public JvmException(Exception e, CommonError commonError, String msg) {
        super();

        this.exception = e;
        this.commonError = commonError;
        this.commonError.setErrMsg(msg);
    }

    public String getExceptionMsg() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        this.exception.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    public boolean isExceptionNull() {
        return this.exception == null;
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public void setErrMsg(String msg) {
        this.commonError.setErrMsg(msg);
    }
}
