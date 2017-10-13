package com.game.classes.exceptions;

public class LevelBadSizeException extends Exception{
    public LevelBadSizeException() { super(); }
    public LevelBadSizeException(String message) { super(message); }
    public LevelBadSizeException(String message, Throwable cause) { super(message, cause); }
    public LevelBadSizeException(Throwable cause) { super(cause); }
}
