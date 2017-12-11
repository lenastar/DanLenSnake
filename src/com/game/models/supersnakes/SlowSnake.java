package com.game.models.supersnakes;

import com.game.classes.enumerators.Direction;
import com.game.models.Snake;

import java.awt.*;

public class SlowSnake extends Snake {
    private int currentSpeed;

    public SlowSnake(Point head, int length, Direction direction) {
        super(head, length, direction);
    }

    public SlowSnake(Point head, int length) {
        super(head, length);
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}
