package com.qsr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ThrowValidException extends RuntimeException{
    public ThrowValidException(String msg){
        super(msg);
    }
}