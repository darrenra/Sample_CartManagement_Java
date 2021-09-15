package com.shopping.cartmanagement.exception;

public class ItemNotFoundException extends Exception {
    private String message;

    public ItemNotFoundException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
