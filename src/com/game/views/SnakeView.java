package com.game.views;

import com.game.classes.Context;
import com.game.classes.interfaces.IView;
import com.game.models.Snake;

import java.awt.*;

public class SnakeView implements IView<Snake, Context> {
    private final Snake model;

    public SnakeView(Snake model){
        this.model = model;
    }

    @Override
    public Snake getModel() {
        return model;
    }

    @Override
    public void paint(Context context) throws IndexOutOfBoundsException {
        for (Point point: model.getSegments()){
            if (point.x < 0
                    || point.y < 0
                    || point.x >= context.map.getLevel().getWidth()
                    || point.y >= context.map.getLevel().getHeight())
                throw new IndexOutOfBoundsException();
            context.map.drawPoint(context.g,point,Color.PINK);
        }
        context.map.drawPoint(context.g, model.getHead(),Color.RED);
    }
}
