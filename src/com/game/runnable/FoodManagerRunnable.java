package com.game.runnable;

import com.game.classes.Game;
import com.game.classes.interfaces.IMap;
import com.game.classes.interfaces.IRunnable;
import com.game.models.Food;
import com.game.models.FoodManager;

import java.awt.*;
import java.util.Random;
import java.util.function.Predicate;

public class FoodManagerRunnable implements IRunnable {
    public Random random = new Random();
    public FoodManager model;

    public FoodManagerRunnable(FoodManager foodManager){
        model = foodManager;
    }

    @Override
    public boolean run(Game game) {
        if (model.getLastEatenFood() != null){
            game.addScores(model.getLastEatenFood().getScores());
        }
        addFoodRandomly(game);
        return true;
    }

    private void addFoodRandomly(Game game) {
        Predicate<Point> isCollision =
                point -> game
                    .getContainerModels()
                    .stream()
                    .anyMatch(model -> model.isCollisionWith(point));
        Point point = addFoodRandomly(game.getMap(), isCollision);
        synchronized (model){
        model.addFood(new Food(point, 1));
        }
    }

    private Point addFoodRandomly(IMap map, Predicate<Point> isCollision) {
        Point point;
        do {
            int x = random.nextInt((int)map.getDimension().getWidth());
            int y = random.nextInt((int)map.getDimension().getHeight());
            point = new Point(x, y);
        } while (isCollision.test(point))   ;
        return point;
    }
}
