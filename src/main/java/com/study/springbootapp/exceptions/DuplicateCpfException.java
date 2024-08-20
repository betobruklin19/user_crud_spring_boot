package com.study.springbootapp.exceptions;

public class DuplicateCpfException extends RuntimeException{

    public DuplicateCpfException(String message){
        super(message);
    }
}
