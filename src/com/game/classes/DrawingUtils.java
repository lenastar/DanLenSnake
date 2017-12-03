package com.game.classes;

import java.awt.*;

public class DrawingUtils {
    public static void drawPoint(Graphics g, Point point,int cellSize, Color color)  {
        g.setColor(color);
        g.fillRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
    }

    public  static void drawImagePoint(Graphics g, int cellSize, Point point, Image image) {
        g.drawImage(image,point.x * cellSize, point.y * cellSize, cellSize, cellSize,null);
    }
}

