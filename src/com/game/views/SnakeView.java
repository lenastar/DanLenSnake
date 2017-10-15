package com.game.views;

import com.game.classes.Context;
import com.game.classes.interfaces.IView;
import com.game.models.Snake;

import java.awt.*;

public class SnakeView implements IView<Snake, Context> {
    private final Snake Model;

    public SnakeView(Snake model){
        Model = model;
    }

    @Override
    public Snake getModel() {
        return Model;
    }

    @Override
    public void paint(Context context) throws IndexOutOfBoundsException {
        for (Point point:Model.getSegments()){
            if (point.x<=0 || point.y<=0 || point.x >= context.map.getWidth() || point.y >= context.map.getHeight())
                throw new IndexOutOfBoundsException();
            context.map.drawPoint(context.g,point,Color.PINK);
        }
        context.map.drawPoint(context.g,Model.getHead(),Color.RED);
    }
}
