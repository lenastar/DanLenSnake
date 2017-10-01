package com.game.classes;

import com.game.classes.IView;

import java.lang.reflect.Method;

public interface IController<TModel, TView extends IView >{
    public TModel getModel();
    public TView getView();
    public Method getAction(int key);
}