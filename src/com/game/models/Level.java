package com.game.models;

import com.game.classes.GameSerializable;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.interfaces.IModel;

import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public class Level extends GameSerializable implements IModel {
    private ArrayList<Point> walls;
    private int width;
    private int height;
    private String name;
    private static final String path = "src/com/game/resources/data/levels/";

    private Level(String[] lines, String name) throws LevelBadSizeException {
        this.name = name;
        width = lines[0].length();
        height = lines.length;
        walls = new ArrayList<>();
        if (width <= 2 || height <= 2) {
            throw new LevelBadSizeException();
        }
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                if (lines[i].charAt(j) == '#'){
                    walls.add(new Point(j, i));
                }
            }
        }
    }

    private Level(int width, int height, ArrayList<Point> walls, String name) throws LevelBadSizeException {
        this.name = name;
        this.width = width;
        this.height = height;
        if (width <= 2 || height <= 2) {
            throw new LevelBadSizeException();
        }
        this.walls = new ArrayList<>(walls);
    }

    public String getFullPath(){
        return Paths.get(path, "default", name, ".dat").toString();
    }

    public static String getFullPath(String name){
        return Paths.get(path, "default", name, ".dat").toString();
    }

    public static Level getDefaultLevel(int width, int height, String name) throws LevelBadSizeException {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < width; i++){
            points.add(new Point(i, 0));
            points.add(new Point(i, height - 1));
        }
        for (int i = 1; i < height - 1; i++) {
            points.add(new Point(0, i));
            points.add(new Point(width - 1, i));
        }
        return new Level(width, height, points, name);
    }

    public static Level getDefaultLevel(int width, int height) throws LevelBadSizeException {
        return getDefaultLevel(width, height, UUID.randomUUID().toString());
    }

    public static Level getFromList(String[] lines, String name) throws LevelBadSizeException {
        return new Level(lines, name);
    }

    public static Level getFromList(String[] lines) throws LevelBadSizeException{
        return getFromList(lines, UUID.randomUUID().toString());
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Point> getWalls() {
        return walls;
    }

    public boolean isCollisionWith(Point point) {
        return walls.stream().anyMatch(p -> p.equals(point));
    }

    public boolean snakeIsAliveAfterCollision(Snake snake){
        return !isCollisionWith(snake.getHead());
    }
}
