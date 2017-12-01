package com.levelConstructor.models;

import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Level;

import java.awt.*;
import java.util.ArrayList;

public class Constructor {
    private Level level;
    private ArrayList<Point> respawns;
    private ArrayList<Point> walls;


    public Constructor(int width, int height, String name) throws LevelBadSizeException {
        respawns = new ArrayList<>();
        walls = new ArrayList<>();
        level = new Level(width, height, walls, respawns, name);
    }

    public void addRangeWalls(ArrayList<Point> range){
        walls.addAll(range);
    }

    public void addRespawn(Point respawn){
        respawns.add(respawn);
    }

    public void rename(String name){
        level.setName(name);
    }

    public Level getLevel() {
        return level;
    }
}
