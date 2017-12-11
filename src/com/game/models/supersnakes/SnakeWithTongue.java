package com.game.models.supersnakes;

import com.game.classes.enumerators.Direction;
import com.game.models.Snake;

import java.awt.*;

public class SnakeWithTongue extends Snake{
    private boolean isTongueOut = false;

    public SnakeWithTongue(Point head, int length, Direction direction) {
        super(head, length, direction);
    }

    public SnakeWithTongue(Point head, int length) {
        super(head, length);
    }

    public boolean isTongueOut() {
        return isTongueOut;
    }

    public void setTongueOut(boolean tongueOut) {
        isTongueOut = tongueOut;
    }
}
