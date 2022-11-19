package io.github.cartoes.mscreditappraiser.exception;

public class ClientDataNotFoundException extends Exception{
    public ClientDataNotFoundException() {
        super("Client Data Not Found. Sorry!");
    }
}
