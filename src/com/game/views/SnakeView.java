package com.game.views;

import com.game.classes.Context;
import com.game.classes.Images;
import com.game.classes.interfaces.IView;
import com.game.models.Snake;

import java.awt.*;
import java.util.ConcurrentModificationException;

public class SnakeView implements IView<Context> {
    private final Snake model;

    public SnakeView(Snake model){
        this.model = model;
    }

    public Snake getModel() {
        return model;
    }

    @Override
    public void paint(Context context) throws IndexOutOfBoundsException,ConcurrentModificationException {
        for (Point point: model.getSegments()){
            context.map.drawImagePoint(context.g,point, Images.getBody());
        }
        context.map.drawImagePoint(context.g, model.getHead(),Images.getHead());
    }
}
