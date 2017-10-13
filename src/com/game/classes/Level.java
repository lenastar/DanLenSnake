package com.game.classes;

import com.game.classes.exceptions.LevelBadSizeException;

import java.awt.*;
import java.util.ArrayList;

public class Level {
    private ArrayList<Point> walls;
    private int width;
    private int height;

    private Level(String[] lines) throws LevelBadSizeException {
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

    private Level(int width, int height, ArrayList<Point> walls) throws LevelBadSizeException {
        this.width = width;
        this.height = height;
        if (width <= 2 || height <= 2) {
            throw new LevelBadSizeException();
        }
        this.walls = new ArrayList<>(walls);
    }

    public static Level getDefaultLevel(int width, int height) throws LevelBadSizeException {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < width; i++){
            points.add(new Point(i, 0));
            points.add(new Point(i, height - 1));
        }
        for (int i = 1; i < height - 1; i++) {
            points.add(new Point(0, i));
            points.add(new Point(width - 1, i));
        }
        return new Level(width, height, points);
    }

    public static Level getFromList(String[] lines) throws LevelBadSizeException {
        return new Level(lines);
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

    public boolean isCollision(Point point) {
        return walls.stream().anyMatch(p -> p.equals(point));
    }
}
