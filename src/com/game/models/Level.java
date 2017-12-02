package com.game.models;

import com.game.classes.SerializationUtil;
import com.game.classes.exceptions.GameSerializableException;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.interfaces.IModel;

import java.awt.*;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public class Level implements IModel, Serializable {
    private ArrayList<Point> walls;
    private ArrayList<Point> respawns;
    private static int width;
    private static int height;
    private String name;
    private static final String path = "src/com/game/resources/levels/";

    private Level(String[] lines, String name) throws LevelBadSizeException {
        this.name = name;
        width = lines[0].length();
        height = lines.length;
        walls = new ArrayList<>();
        respawns = new ArrayList<>();
        if (width <= 2 || height <= 2) {
            throw new LevelBadSizeException();
        }
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                if (lines[i].charAt(j) == '#'){
                    walls.add(new Point(j, i));
                }
                else if (lines[i].charAt(j) == '%'){
                    respawns.add(new Point(j, i));
                }
            }
        }
    }

    public Level(int width, int height, ArrayList<Point> walls, ArrayList<Point> respawns, String name) throws LevelBadSizeException {
        this.respawns = respawns;
        this.name = name;
        this.width = width;
        this.height = height;
        if (width <= 2 || height <= 2) {
            throw new LevelBadSizeException();
        }
        this.walls = new ArrayList<>(walls);
    }

    public void save(){
        SerializationUtil.save(getFullPath(), this);
    }

    public void save(String path){
        SerializationUtil.save(path, this);
    }

    public static Level get(String path) throws GameSerializableException {
        return (Level)SerializationUtil.get(path);
    }

    public String getFullPath(){
        return getFullPath(name);
    }

    public static String getFullPath(String name){
        return Paths.get(path, "default", name + ".dat").toString();
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
        ArrayList<Point> respawns = new ArrayList<>();
        respawns.add(new Point(width / 2, height / 2));
        return new Level(width, height, points, respawns, name);
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

    public static void SetHeight(int heigh){
        height = heigh;
    }


    public int getWidth() {
        return width;
    }

    public static void SetWidth(int widt){
        width= widt;
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

    public ArrayList<Point> getRespawns() {
        return respawns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
