package com.game.models;

import com.game.classes.interfaces.IModel;

import java.awt.*;

public class Food{
    private int scores;
    private Point location;

    public Food(Point location, int scores) {
        this.scores = scores;
        this.location = location;
    }

    public int getScores() {
        return scores;
    }

    public Point getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Food &&
                o.hashCode() == hashCode());
    }

    @Override
    public int hashCode() {
        return 211*211* location.x + 211* location.y + scores;
    }
}
