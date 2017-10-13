package com.game.classes.interfaces;

public interface IView<TModel, TContext>{
    TModel getModel();
    void paint(TContext context) throws IndexOutOfBoundsException;
}