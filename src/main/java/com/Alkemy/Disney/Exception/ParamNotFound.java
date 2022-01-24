package com.Alkemy.Disney.Exception;

public class ParamNotFound extends RuntimeException{
    public ParamNotFound(String error){
        super(error);
    }
}
