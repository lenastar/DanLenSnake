package com.game.classes.interfaces;

import java.io.Serializable;

public interface IController extends Serializable{
    void runAction(int key);
    boolean keyExists(int key);
}