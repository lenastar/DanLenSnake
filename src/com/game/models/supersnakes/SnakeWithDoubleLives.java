package com.game.models.supersnakes;

import com.game.classes.enumerators.Direction;
import com.game.models.Snake;

import java.awt.*;

public class SnakeWithDoubleLives extends Snake{
    private boolean wasDead = false;

    public SnakeWithDoubleLives(Point head, int length, Direction direction) {
        super(head, length, direction);
    }

    public SnakeWithDoubleLives(Point head, int length) {
        super(head, length);
    }

    public boolean isWasDead() {
        return wasDead;
    }

    public void setWasDead(boolean wasDead) {
        this.wasDead = wasDead;
    }
}
