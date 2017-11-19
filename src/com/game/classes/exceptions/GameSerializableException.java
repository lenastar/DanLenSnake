package com.game.classes.exceptions;

public class GameSerializableException extends Exception{
    public GameSerializableException() { super("File is empty or corrupted"); }
}
