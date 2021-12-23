package ru.spbu.mas.exception;

import java.security.InvalidParameterException;

public class InvalidArgumentException extends InvalidParameterException {

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
