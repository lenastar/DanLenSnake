package com.game.classes;

import com.game.classes.interfaces.IMap;
import com.game.models.Food;

import java.awt.*;
import java.util.Random;
import java.util.function.Predicate;

public class Randomize {
    public static Random random = new Random();

    public static void addFoodRandomly(Game game){
        Predicate<Point> isCollision = point -> game.getMap().getLevel().isCollision(point)
                || game.getContainerModels()
                                .stream()
                                .anyMatch(model -> model.isCollisionWith(point))
                || game.getFoodManager().isCollisionWith(point);
        Point point = addFoodRandomly(game.getMap().getLevel(), isCollision);
        game.addFood(new Food(point, 1));
    }

    private static Point addFoodRandomly(Level level, Predicate<Point> isCollision) {
        Point point;
        do {
            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getHeight());
            point = new Point(x, y);
        }while (isCollision.test(point));
        return point;
    }
}
