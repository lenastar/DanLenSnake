package com.levelConstructor.models;

import com.game.classes.SerializationUtil;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Level;

import java.awt.*;
import java.util.ArrayList;

public class Constructor {
    private Level level;
    private ArrayList<Point> respawns;
    private ArrayList<Point> walls;
    private ArrayList<Point> buffer;


    public Constructor(int width, int height, String name) throws LevelBadSizeException {
        respawns = new ArrayList<>();
        walls = new ArrayList<>();
        buffer = new ArrayList<>();
        level = new Level(width, height, walls, respawns, name);
    }

    public Constructor(Level level){
        this.level = level;
        respawns = level.getRespawns();
        walls = level.getWalls();
        buffer = new ArrayList<>();
    }

    public void addBufferToWalls(){
        walls.addAll(buffer);
    }

    public void addRespawn(Point respawn){
        respawns.add(respawn);
    }

    public void rename(String name){
        level.setName(name);
    }

    public void clearWalls(){
        walls.clear();
    }

    public void clearRespawns(){
        respawns.clear();
    }

    public void  clearLevel(){
        walls.clear();
        respawns.clear();
    }

    public void removeBufferFromWalls(){
        walls.removeAll(buffer);
    }

    public void clearBuffer(){
        buffer.clear();
    }

    public void addRangeToBuffer(ArrayList<Point> range){
        buffer.addAll(range);
    }

    public void addPointToBuffer(Point point){
        buffer.add(point);
    }

    public void removeRangeFromBuffer(ArrayList<Point> range){
        buffer.removeAll(range);
    }

    public void  removeRespawn(Point point){
        respawns.remove(point);
    }

    public void removePointFromWalls(Point point){
        walls.remove(point);
    }

    public void save(String path){
        SerializationUtil.save(path, level);
    }

    public Level getLevel() {
        return level;
    }

    public ArrayList<Point> getRespawns() {
        return respawns;
    }

    public ArrayList<Point> getWalls() {
        return walls;
    }

    public ArrayList<Point> getBuffer() {
        return buffer;
    }

    public void setLevel(Level level){
        this.level = level;
        this.walls = level.getWalls();
        this.respawns = level.getRespawns();
        this.buffer = new ArrayList<>();
    }
}
