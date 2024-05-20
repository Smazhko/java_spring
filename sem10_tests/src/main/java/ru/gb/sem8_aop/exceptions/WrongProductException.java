package ru.gb.sem8_aop.exceptions;

public class WrongProductException extends IllegalArgumentException{
    public WrongProductException(){
        super("Product must have non-null and non-zero fields.");
    };
}
