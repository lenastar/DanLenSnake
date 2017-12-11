package com.game.models.supersnakes;

import com.game.classes.enumerators.Direction;
import com.game.models.Snake;

import java.awt.*;

public class SupershortSnake extends Snake{
    private int countEaten = 0;

    public SupershortSnake(Point head, int length, Direction direction) {
        super(head, length, direction);
    }

    public SupershortSnake(Point head, int length) {
        super(head, length);
    }

    public int getCountEaten() {
        return countEaten;
    }

    public void incCountEaten() {
        countEaten++;
    }

    public void cleanCountEaten(){
        countEaten = 0;
    }

    public void becomeShort(){
        setLength(getLength() / 2);
    }
}
