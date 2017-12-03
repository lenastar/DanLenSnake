package com.levelConstructor.classes;

import com.levelConstructor.models.Constructor;
import com.levelConstructor.views.ConstructorView;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConstructorMouseAdapter extends MouseInputAdapter {
    private final ConstructorView constructorView;
    private final Constructor constructor;
    private Point pressed;

    public ConstructorMouseAdapter(ConstructorView constructorView) {
        this.constructorView = constructorView;
        this.constructor = constructorView.getConstructor();
        this.pressed = null;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (pressed != null){
            constructor.removeBufferFromWalls();
            constructor.clearBuffer();
            List<Integer> xRange = Arrays.asList(pressed.x, mouseEvent.getX());
            List<Integer> yRange = Arrays.asList(pressed.y, mouseEvent.getY());
            for (int x = Collections.min(xRange) / constructorView.getCellSize();
                 x <= Collections.max(xRange) / constructorView.getCellSize();
                 x++){
                for (int y = Collections.min(yRange) / constructorView.getCellSize();
                        y <= Collections.max(yRange) / constructorView.getCellSize();
                        y++){
                    if (x < constructorView.getConstructor().getLevel().getWidth()
                            && y < constructorView.getConstructor().getLevel().getHeight()) {
                        Point point = new Point(x, y);
                        constructor.addPointToBuffer(point);
                        constructor.removeRespawn(point);
                    }
                }
            }
            this.constructor.addBufferToWalls();
            constructorView.repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        int notches = mouseWheelEvent.getWheelRotation();
        constructorView.setCellSize(constructorView.getCellSize() - notches);
        constructorView.setDimension();
        constructorView.getParent().revalidate();
        constructorView.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent){
        this.pressed = mouseEvent.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent){
        this.pressed = null;
        constructor.clearBuffer();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = new Point(mouseEvent.getX() / constructorView.getCellSize(),
                mouseEvent.getY() / constructorView.getCellSize());
        if (!constructor.getRespawns().contains(point)) {
            constructor.addRespawn(point);
            constructorView.repaint();
        }
    }
}

