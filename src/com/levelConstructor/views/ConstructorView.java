package com.levelConstructor.views;

import com.game.classes.DrawingUtils;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Level;
import com.levelConstructor.classes.ConstructorMouseAdapter;
import com.levelConstructor.classes.Eraser;
import com.levelConstructor.models.Constructor;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;

public class ConstructorView extends JPanel{
    private Constructor constructor;
    private int cellSize;

    private MouseInputAdapter mouseInputAdapter;
    private Eraser eraser;

    public ConstructorView(int width, int height, String name, int cellSize) throws LevelBadSizeException {
        this.constructor = new Constructor(width, height, name);
        this.cellSize = cellSize;
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
        int width = constructor.getLevel().getWidth();
        int height = constructor.getLevel().getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                DrawingUtils.drawPoint(graphics,new Point(x,y),cellSize,Color.WHITE);
            }
        }

        for (Point point: constructor.getRespawns()){
            DrawingUtils.drawPoint(graphics, point, cellSize, Color.RED);
        }

        for (Point point: constructor.getWalls()){
            DrawingUtils.drawPoint(graphics, point, cellSize, Color.LIGHT_GRAY);
        }
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
        try {
            constructor = new Constructor(value, constructor.getLevel().getHeight(), constructor.getLevel().getName());
            changeAdapters();

        } catch (LevelBadSizeException e) {
            e.printStackTrace();
        }
    }

    public void setHeight(int value){
        try {
            constructor = new Constructor(constructor.getLevel().getWidth(), value, constructor.getLevel().getName());
            changeAdapters();
        } catch (LevelBadSizeException e) {
            e.printStackTrace();
        }
    }

    public void changeLevel(Level level){
        constructor = new Constructor(level);
        setDrawingMode();
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
        int width = constructor.getLevel().getWidth();
        int height = constructor.getLevel().getHeight();
        setPreferredSize(new Dimension(width * cellSize, height * cellSize));
    }

    private void changeAdapters(){
        mouseInputAdapter=new ConstructorMouseAdapter(this);
        eraser = new Eraser(this);
        setDrawingMode();
        repaint();
    }

}
