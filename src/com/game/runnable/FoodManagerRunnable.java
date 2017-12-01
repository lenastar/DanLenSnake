package com.game.runnable;

import com.game.classes.Game;
import com.game.classes.RandomUtils;
import com.game.classes.interfaces.IMap;
import com.game.classes.interfaces.IRunnable;
import com.game.models.Food;
import com.game.models.FoodManager;

import java.awt.*;
import java.util.Random;
import java.util.function.Predicate;

public class FoodManagerRunnable implements IRunnable {
    public final FoodManager model;

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
        Point point = RandomUtils.addFoodRandomly(game.getMap(), isCollision);
        synchronized (model){
            model.addFood(new Food(point, 1));
        }
    }
}
