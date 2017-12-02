package com.levelConstructor.classes;

import com.levelConstructor.models.Constructor;
import com.levelConstructor.views.ConstructorView;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Eraser extends MouseInputAdapter{
    private final ConstructorView constructorView;
    private final Constructor constructor;

    public Eraser(ConstructorView constructorView) {
        this.constructorView = constructorView;
        this.constructor = constructorView.getConstructor();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point point = new Point(mouseEvent.getX() / constructorView.getCellSize(),
                mouseEvent.getY() / constructorView.getCellSize());
        constructor.removeRespawn(point);
        constructor.removePointFromWalls(point);
        constructorView.repaint();
    }
}
