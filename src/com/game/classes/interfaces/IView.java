package com.game.classes.interfaces;

import java.io.Serializable;

public interface IView<TContext>{
    void paint(TContext context) throws IndexOutOfBoundsException;
}