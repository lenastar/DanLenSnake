package com.game.classes;

public interface IView<TModel>{
    public TModel getModel();
    public void paint(IMap map) throws IndexOutOfBoundsException;
}