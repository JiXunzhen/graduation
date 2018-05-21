package com.gayson.exception;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by jixunzhen on 2018/5/21.
 */
public class ApplicationException extends Exception{
    public ErrorCode code;
    public String errorMsg;
    private Serializable data;

    private static HashMap<ErrorCode, String> defaultMsgs = new HashMap<>();
    static {
        defaultMsgs.put(ErrorCode.DATA_EXIST, "对象已存在");
        defaultMsgs.put(ErrorCode.NOT_FOUND, "对象不存在");
        defaultMsgs.put(ErrorCode.PLATFORM_TYPE_ERROR, "平台类型有误");
        defaultMsgs.put(ErrorCode.PERMISSION_DENIED, "权限不足");
    }
    public ApplicationException(ErrorCode code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ApplicationException(ErrorCode code) {
        this.code = code;
        this.errorMsg = defaultMsgs.get(code);
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
}
