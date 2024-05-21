package ru.gb.sem11_prometheus.exceptions;

public class WrongProductException extends IllegalArgumentException{
    public WrongProductException(){
        super("Product must have non-null and non-zero fields.");
    };
}
