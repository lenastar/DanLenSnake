package com.game.classes;

import com.game.models.Map;

public interface IView<TModel>{
    public TModel getModel();
    public void draw(Map map) throws IndexOutOfBoundsException;
}