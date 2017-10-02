package com.game.views;

import com.game.classes.IView;
import com.game.classes.SimpleObjects;
import com.game.models.Map;
import com.game.models.Snake;

import java.awt.*;
import java.util.function.*;

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
    public void draw(Map map) throws IndexOutOfBoundsException {

        for (Point segment: Model.getSegments()){
            map.setItem(segment, SimpleObjects.SnakeBody);
        }
        map.setItem(Model.getHead(),SimpleObjects.SnakeHead);

    }
}
