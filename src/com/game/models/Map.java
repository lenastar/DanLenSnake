package com.game.models;

import com.game.classes.SimpleObjects;

public class Map {
    public int getHeight() {
        return Height;
    }

    private int Height;

    public int getWidth() {
        return Width;
    }

    private int Width;

    public char[][] getCanvas() {
        return Canvas;
    }

    private char[][] Canvas;

    public char getItem(int x, int y){
        return Canvas[x][y];
    }

    public void setItem(int x, int y, char item){
        Canvas[x][y] = item;
    }

    public Map(int height, int width){
        Height = height;
        Width = width;
        Canvas = new char[width][height];
        fillMap();
    }

    public void fillMap(){
        for (int i = 1; i  < Width-1; i++) {
            Canvas[i][0] = SimpleObjects.HorizonWall;
            Canvas[i][Height-1] = SimpleObjects.HorizonWall;
        }
        for (int i = 1; i<Height-1; i++){
            Canvas[0][i] = SimpleObjects.VerticalWall;
            Canvas[Width-1][i] = SimpleObjects.VerticalWall;
        }
        for (int i = 1; i<Width-1; i++) {
            for (int j = 1; j < Height - 1; j++){
                Canvas[i][j] = SimpleObjects.Empty;
            }
        }

        Canvas[0][0] = SimpleObjects.Corner;
        Canvas[Width-1][0] = SimpleObjects.Corner;
        Canvas[0][Height-1] = SimpleObjects.Corner;
        Canvas[Width-1][Height-1] = SimpleObjects.Corner;
    }

    public void print(){
        for (int i = 0; i < Width; i++){
            for (int j = 0; j < Height;j++){
                System.out.print(Canvas[i][j]);
            }
            System.out.println();
        }
    }
}
