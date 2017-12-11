package com.game.models.supersnakes;

import com.game.classes.enumerators.Direction;
import com.game.models.Snake;

import java.awt.*;

public class IntangibleSnake extends Snake{
    private boolean intangibility = false;

    public IntangibleSnake(Point head, int length, Direction direction) {
        super(head, length, direction);
    }

    public IntangibleSnake(Point head, int length) {
        super(head, length);
    }

    public boolean isIntangibility() {
        return intangibility;
    }

    public void setIntangibility(boolean intangibility) {
        this.intangibility = intangibility;
    }
}
