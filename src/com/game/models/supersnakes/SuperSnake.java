package com.game.models.supersnakes;

import com.game.classes.enumerators.Direction;
import com.game.models.Snake;

import java.awt.*;

public class SuperSnake extends Snake{
    private boolean superMode = false;

    public SuperSnake(Point head, int length, Direction direction) {
        super(head, length, direction);
    }

    public SuperSnake(Point head, int length) {
        super(head, length);
    }

    public boolean isSuperMode() {
        return superMode;
    }

    public void setSuperMode(boolean superMode) {
        this.superMode = superMode;
    }
}
