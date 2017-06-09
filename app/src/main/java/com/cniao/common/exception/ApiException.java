package com.cniao.common.exception;

/**
 * Created by chenqi on 2017/6/9.
 */

public class ApiException extends BaseException {
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
