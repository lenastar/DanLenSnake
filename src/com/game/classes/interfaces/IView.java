package com.game.classes.interfaces;

public interface IView<TModel extends IModel, TContext>{
    TModel getModel();
    void paint(TContext context) throws IndexOutOfBoundsException;
}