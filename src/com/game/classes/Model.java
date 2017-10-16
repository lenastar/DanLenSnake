package com.game.classes;

import com.game.classes.enumerators.Direction;
import com.game.controllers.SnakeController;
import com.game.models.Snake;
import com.game.runnable.SnakeRunnable;
import com.game.views.SnakeView;

import java.awt.*;

public class Model {
    public static Instance createSnake(Point head, int length, Direction direction)
            throws NoSuchMethodException {
        Snake snake = new Snake(head, length, direction);
        SnakeView snakeView = new SnakeView(snake);
        SnakeController snakeController = new SnakeController(snake);
        SnakeRunnable snakeRunnable = new SnakeRunnable(snake);
        return new Instance(snake, snakeView, snakeController, snakeRunnable);
    }

    public static Instance createSnake(Point head, int length)
            throws NoSuchMethodException {
        return createSnake(head, length, Direction.Left);
    }
}
