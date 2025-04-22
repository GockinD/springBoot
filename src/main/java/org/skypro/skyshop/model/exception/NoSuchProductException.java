package org.skypro.skyshop.model.exception;

public class NoSuchProductException extends RuntimeException{
    public NoSuchProductException() {
        super("Продукт не найден");
    }
}
