package com.game.classes.interfaces;

import com.game.classes.Game;

import java.io.Serializable;

public interface IRunnable extends Serializable{
    boolean run(Game game);
}
