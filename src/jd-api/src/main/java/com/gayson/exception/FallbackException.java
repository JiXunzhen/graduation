package com.gayson.exception;

/**
 * Created by jixunzhen on 2018/5/21.
 */
public class FallbackException extends Exception{
    Class fallbackObject;
    String service;
    String uri;

    public FallbackException(Class fallbackObject, String service, String uri) {
        this.fallbackObject = fallbackObject;
        this.service = service;
        this.uri = uri;
    }
}
