package com.game.classes.interfaces;

public interface IController<TModel>{
    TModel getModel();
    void runAction(int key);
    boolean keyExists(int key);
}