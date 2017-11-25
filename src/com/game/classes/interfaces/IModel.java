package com.game.classes.interfaces;

import com.game.models.Snake;

import java.awt.*;
import java.io.Serializable;

public interface IModel{
    boolean isCollisionWith(Point point);
    boolean snakeIsAliveAfterCollision(Snake snake);
}
