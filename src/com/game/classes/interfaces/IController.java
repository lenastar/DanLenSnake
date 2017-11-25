package com.game.classes.interfaces;

import java.io.Serializable;

public interface IController{
    void runAction(int key);
    boolean keyExists(int key);
}