package com.example.putdefault.api.controller.exception;

public class NotFoundException extends RuntimeException {

    private String idNotfound;

    public NotFoundException(String idNotFound) {
        super("Not found the resource with id :" + idNotFound);
        this.idNotfound = idNotFound;
    }

    public String getIdNotfound() {
        return idNotfound;
    }
}
