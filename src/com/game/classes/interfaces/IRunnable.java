package com.game.classes.interfaces;

import com.game.classes.Game;

public interface IRunnable<TModel> {
    TModel getModel();
    boolean run(Game game) throws Exception;
}
