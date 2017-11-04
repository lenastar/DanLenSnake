package com.game.runnable;

import com.game.classes.Game;
import com.game.classes.Level;
import com.game.classes.interfaces.IModel;
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
        addFoodRandomly(game);
        return true;
    }

    private void addFoodRandomly(Game game) {
        Predicate<Point> isCollision = point -> game.getMap().getLevel().isCollision(point)
                || game.getContainerModels()
                .stream()
                .anyMatch(model -> model.isCollisionWith(point))
                || model.isCollisionWith(point);
        Point point = addFoodRandomly(game.getMap().getLevel(), isCollision);
        model.addFood(new Food(point, 1));
    }

    private Point addFoodRandomly(Level level, Predicate<Point> isCollision) {
        Point point;
        do {
            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getHeight());
            point = new Point(x, y);
        } while (isCollision.test(point))   ;
        return point;
    }
}
