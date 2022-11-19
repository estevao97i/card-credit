package io.github.cartoes.mscreditappraiser.exception;

import lombok.Getter;

public class ErrorComunicationMicroServicesException extends Exception {

    @Getter
    private final Integer status;

    public ErrorComunicationMicroServicesException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
