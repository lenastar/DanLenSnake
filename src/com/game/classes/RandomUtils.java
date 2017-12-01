package com.game.classes;

import com.game.classes.interfaces.IMap;
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
}
