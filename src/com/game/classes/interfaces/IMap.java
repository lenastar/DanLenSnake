package com.game.classes.interfaces;

import com.game.models.Level;

import java.awt.*;
import java.util.ArrayList;

public interface IMap {
    void paint();
    void addView(IView view);
    ArrayList<IView> getViews();
    Dimension getDimension();
}
