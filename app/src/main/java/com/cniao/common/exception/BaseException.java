package com.cniao.common.exception;

/**
 * Created by chenqi on 2017/6/9.
 */

public class BaseException extends Exception {
    private int code;
    private String displayMessage;

    public BaseException(String message, int code, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
