package com.gayson.globals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * Created by jixunzhen on 2018/5/11.
 */
public class Result {
    private ResultStatus status;
    private Serializable object;
    private String msg;

    private static final Logger logger = LoggerFactory.getLogger(Result.class);

    Result(ResultStatus status, Serializable object, String msg) {
        this.status = status;
        this.object = object;
        this.msg = msg;
    }

    public static Result createResult(ResultStatus status, Serializable object) {
        return new Result(status, object, msgs.get(status));
    }

    public static Result createResult(ResultStatus status, Serializable object, String msg) {
        return new Result(status, object, msg);
    }

    public static Result createError(ResultStatus status, Exception e) {
        logger.error(status.name(), e);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();

        return new Result(status, stackTrace, msgs.get(status));
    }

    private static HashMap<ResultStatus, String> msgs = new HashMap<>();

    static {
        msgs.put(ResultStatus.OK, "正常");
        msgs.put(ResultStatus.USER_ERROR, "错误");
        msgs.put(ResultStatus.AUTHENTICATE_FAIL, "验证失败，密码错误");
        msgs.put(ResultStatus.EXPIRED_TOKEN, "过期的Json Web Token");
        msgs.put(ResultStatus.SYSTEM_ERROR, "系统错误");
        msgs.put(ResultStatus.FALLBACK_ERROR, "服务熔断降级错误");
        msgs.put(ResultStatus.VENDOR_ERROR, "依赖服务错误");
    }


    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public Serializable getObject() {
        return object;
    }

    public void setObject(Serializable object) {
        this.object = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
