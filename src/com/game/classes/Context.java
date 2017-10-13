package com.game.classes;

import java.awt.*;

public class Context {
    public MapGUI map;
    public Graphics g;

    public Context(MapGUI map, Graphics g)
    {
        this.map = map;
        this.g = g;
    }
}
