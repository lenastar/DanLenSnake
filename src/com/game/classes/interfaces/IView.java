package com.game.classes.interfaces;

public interface IView<TContext>{
    void paint(TContext context) throws IndexOutOfBoundsException;
}