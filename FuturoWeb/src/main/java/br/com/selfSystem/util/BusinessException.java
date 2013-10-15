package br.com.selfSystem.util;

import java.io.Serializable;

public class BusinessException extends Exception implements Serializable {

    private static final long serialVersionUID = -3865580623092456248L;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
