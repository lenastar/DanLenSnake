package com.game.classes.exceptions;

public class SnakeOppositeMoveException extends Exception{
    public SnakeOppositeMoveException() { super(); }
    public SnakeOppositeMoveException(String message) { super(message); }
    public SnakeOppositeMoveException(String message, Throwable cause) { super(message, cause); }
    public SnakeOppositeMoveException(Throwable cause) { super(cause); }
}
