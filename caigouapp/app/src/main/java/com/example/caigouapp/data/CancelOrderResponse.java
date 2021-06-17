package com.example.caigouapp.data;

import java.io.Serializable;

public class CancelOrderResponse implements Serializable {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CancelOrderResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
