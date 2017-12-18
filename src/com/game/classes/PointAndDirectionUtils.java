package com.game.classes;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.UnknownDirectionException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Set;

public class PointAndDirectionUtils {
    private static HashMap<Direction, Integer> dirToKeycode = new HashMap<Direction, Integer>(){{
       put(Direction.Up, KeyEvent.VK_UP);
       put(Direction.Down, KeyEvent.VK_DOWN);
       put(Direction.Left, KeyEvent.VK_LEFT);
       put(Direction.Right, KeyEvent.VK_RIGHT);
    }};

    private static HashMap<Integer, Direction> keycodetoDir = new HashMap<Integer, Direction>() {{
        put(KeyEvent.VK_UP, Direction.Up);
        put(KeyEvent.VK_DOWN, Direction.Down);
        put(KeyEvent.VK_LEFT, Direction.Left);
        put(KeyEvent.VK_RIGHT, Direction.Right);
    }};

    public static Set<Integer> keyValues(){
        return keycodetoDir.keySet();
    }

    public static Direction getDirection(Point point_1, Point point_2, int width, int height) throws UnknownDirectionException {
        for (Direction direction: Direction.values()){
            if (isDirection(direction, point_1, point_2, width, height)){
                return direction;
            }
        }
        throw new UnknownDirectionException();
    }

    public static Direction getDirection(int keyCodes){
        return keycodetoDir.get(keyCodes);
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
