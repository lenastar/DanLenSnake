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
        //TODO:implement method
    }
}
