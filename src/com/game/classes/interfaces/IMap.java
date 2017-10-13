package com.game.classes.interfaces;

import com.game.classes.Level;

public interface IMap {
    void paint();
    Level getLevel();
    void addView(IView view);
}
