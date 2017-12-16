package com.game.classes;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.UnknownDirectionException;
import com.game.models.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class PointAndDirectionUtils {
    private static HashMap<Direction, Integer> dirToKeycode = new HashMap<Direction, Integer>(){{
       put(Direction.Up, KeyEvent.VK_UP);
       put(Direction.Down, KeyEvent.VK_DOWN);
       put(Direction.Left, KeyEvent.VK_LEFT);
       put(Direction.Right, KeyEvent.VK_RIGHT);
    }};

    public static Direction getDirection(Point point_1, Point point_2, int width, int height) throws UnknownDirectionException {
        if (isDirection(Direction.Up, point_1, point_2, width, height)){
            return Direction.Up;
        } else if (isDirection(Direction.Down, point_1, point_2, width, height)){
            return Direction.Down;
        } else if (isDirection(Direction.Left, point_1, point_2, width, height)){
            return Direction.Left;
        } else if (isDirection(Direction.Right, point_1, point_2, width, height)){
            return Direction.Right;
        }
        throw new UnknownDirectionException();
    }

    public static int getKeycode(Direction direction){
        return dirToKeycode.get(direction);
    }

    public static Point moveCyclically(int x, int y, int width, int height){
        return new Point((x + width) % width, (y + height) % height);
    }

    public static Point moveCyclically(Point point, int width, int height){
        return moveCyclically(point.x, point.y, width, height);
    }

    public static Point add(Point point_1, Point point_2){
        return new Point(point_1.x + point_2.x, point_1.y + point_2.y);
    }

    private static boolean isDirection(Direction direction, Point point_1, Point point_2, int width, int height){
        return moveCyclically(add(point_1, direction.getPoint()), width, height).equals(point_2);
    }
}
