package com.game.classes;

import com.game.classes.interfaces.IMap;
import com.game.models.FoodManager;
import com.game.models.Level;

import java.awt.*;
import java.util.Random;
import java.util.function.Predicate;

public class RandomUtils {
    private static Random random = new Random();

    public static Point addFoodRandomly(IMap map, Predicate<Point> isCollision) {
        Point point;
        do {
            int x = random.nextInt((int)map.getDimension().getWidth());
            int y = random.nextInt((int)map.getDimension().getHeight());
            point = new Point(x, y);
        } while (isCollision.test(point))   ;
        return point;
    }

    public static Point getRespawnPoint(Level level){
        return level.getRespawns().get(random.nextInt(level.getRespawns().size()));
    }

    public static Point getFood(FoodManager foodManager){
        int randInt = 0;
        if (foodManager.getLocations().size() > 0) {
            randInt = random.nextInt(foodManager.getLocations().size());
        }
        int index = 0;
        for (Point point: foodManager.getLocations()){
            if (index == randInt){
                return point;
            }
            index++;
        }
        return new Point(0, 0);
    }
}
