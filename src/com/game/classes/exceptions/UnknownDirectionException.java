package com.game.classes.exceptions;

public class UnknownDirectionException extends Exception{
    public UnknownDirectionException() {
        super("Direction is unknown");
    }
}
