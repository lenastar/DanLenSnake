package com.game.views;

import com.game.classes.IView;
import com.game.classes.IMap;
import com.game.classes.SimpleObjects;
import com.game.models.MapConsole;
import com.game.models.Snake;

import java.awt.*;

public class SnakeView implements IView<Snake> {
    private final Snake Model;

    public SnakeView(Snake model){
        Model = model;
    }

    @Override
    public Snake getModel() {
        return Model;
    }

    @Override
    public void paint(IMap map) throws IndexOutOfBoundsException {
        draw((MapConsole) map);
    }

    private void draw(MapConsole mapConsole) throws IndexOutOfBoundsException {

        for (Point segment: Model.getSegments()){
            mapConsole.setItem(segment, SimpleObjects.SnakeBody);
        }
        mapConsole.setItem(Model.getHead(),SimpleObjects.SnakeHead);

    }
}
