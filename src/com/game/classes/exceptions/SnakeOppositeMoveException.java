package com.game.classes.exceptions;

public class SnakeOppositeMoveException extends Exception{
    public SnakeOppositeMoveException() { super("Snake can't move in opposite direction"); }
}
