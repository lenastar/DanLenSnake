package com.levelConstructor.views;

import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Level;
import com.levelConstructor.Main;
import com.levelConstructor.classes.ConstructorMouseAdapter;
import com.levelConstructor.classes.Eraser;
import com.levelConstructor.models.Constructor;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.util.Arrays;

public class ConstructorView extends JPanel{
    private final Constructor constructor;
    private int cellSize;
    private int width;
    private int height;
    private final MouseInputAdapter mouseInputAdapter;
    private final Eraser eraser;

    public ConstructorView(int width, int height, String name, int cellSize) throws LevelBadSizeException {
        this.constructor = new Constructor(width, height, name);
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        setDimension();
        setBackground(Color.WHITE);
        mouseInputAdapter = new ConstructorMouseAdapter(this);
        eraser = new Eraser(this);
        addMouseWheelListener(mouseInputAdapter);
        setDrawingMode();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                graphics.setColor(Color.WHITE);
                graphics.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                graphics.setColor(Color.LIGHT_GRAY);
                graphics.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        for (Point point: constructor.getRespawns()){
            drawPoint(graphics, point, Color.RED);
        }

        for (Point point: constructor.getWalls()){
            drawPoint(graphics, point, Color.DARK_GRAY);
        }
    }

    private void drawPoint(Graphics g, Point point, Color color) throws IndexOutOfBoundsException{
        g.setColor(color);
        g.fillRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int value){
        cellSize = value;
    }

    public void setWidth(int value){
        width = value;
    }

    public void setHeight(int value){
        height = value;
    }

    public void changeLevel(Level level){
        constructor.setLevel(level);
        repaint();
    }

    public void setEraseMode(){
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeMouseListener(mouseInputAdapter);
        removeMouseMotionListener(mouseInputAdapter);
        addMouseMotionListener(eraser);
    }

    public void setDrawingMode(){
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        removeMouseMotionListener(eraser);
        addMouseMotionListener(mouseInputAdapter);
        addMouseListener(mouseInputAdapter);
    }

    public void setDimension(){
        setPreferredSize(new Dimension(width * cellSize, height * cellSize));
    }

}
