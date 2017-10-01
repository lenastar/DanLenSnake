package com.game.views;

import com.game.classes.IView;
import com.game.classes.SimpleObjects;
import com.game.models.Map;
import com.game.models.Snake;

import java.awt.*;

public class SnakeView implements IView<Snake> {
    private Snake Model;

    public SnakeView(Snake model){
        Model = model;
    }

    @Override
    public Snake getModel() {
        return Model;
    }

    @Override
    public void draw(Map map) throws Exception {

        for (Point segment: Model.getSegments()){
            if (segment.x < 0 || segment.y<0 || segment.x >= map.getWidth() || segment.y >= map.getHeight()){
                throw new Exception("Snake is outside of map");
            }
            map.setItem(segment.x,segment.y, SimpleObjects.SnakeBody);
        }
        map.setItem(Model.getHead().x,Model.getHead().y,SimpleObjects.SnakeHead);


    }
}
