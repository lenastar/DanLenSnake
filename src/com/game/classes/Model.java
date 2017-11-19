package com.game.classes;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.controllers.SnakeController;
import com.game.models.FoodManager;
import com.game.models.Level;
import com.game.models.Snake;
import com.game.runnable.FoodManagerRunnable;
import com.game.runnable.SnakeRunnable;
import com.game.views.FoodManagerView;
import com.game.views.LevelView;
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

    public static Instance createFoodManager(int limit){
        FoodManager foodManager = new FoodManager(limit);
        FoodManagerView foodManagerView = new FoodManagerView(foodManager);
        FoodManagerRunnable foodManagerRunnable = new FoodManagerRunnable(foodManager);
        return new Instance(foodManager, foodManagerView, null, foodManagerRunnable);
    }

    public static Instance createLevel(Level level){
        LevelView levelView = new LevelView(level);
        return new Instance(level, levelView, null, null);
    }

    public static Instance createLevel(int width, int height) throws LevelBadSizeException {
        Level level = Level.getDefaultLevel(width, height);
        LevelView levelView = new LevelView(level);
        return new Instance(level, levelView, null, null);
    }
}
