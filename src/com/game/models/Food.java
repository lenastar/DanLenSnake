package com.game.models;

import java.awt.*;

public class Food {
    private int Scores;
    private Point Location;

    public Food(Point location, int scores) {
        Scores = scores;
        Location = location;
    }

    public int getScores() {
        return Scores;
    }

    public Point getLocation() {
        return Location;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Food &&
                o.hashCode() == hashCode());
    }

    @Override
    public int hashCode() {
        return 211*211*Location.x + 211*Location.y + Scores;
    }
}
