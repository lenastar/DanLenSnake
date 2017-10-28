package com.game.classes.interfaces;

import com.game.classes.Level;

import java.util.ArrayList;

public interface IMap {
    void paint();
    Level getLevel();
    void addView(IView view);
    ArrayList<IView> getViews();
}
