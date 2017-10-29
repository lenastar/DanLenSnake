package com.game.classes.interfaces;

import com.game.classes.Game;

public interface IRunnable<TModel extends IModel> {
    TModel getModel();
    boolean run(Game game);
}
