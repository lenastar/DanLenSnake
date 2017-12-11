package com.game.views;

import com.game.classes.Context;
import com.game.classes.Images;
import com.game.classes.interfaces.IView;
import com.game.models.Snake;

import java.awt.*;
import java.util.ConcurrentModificationException;

public class SnakeView extends AbstractSnakeView{

    public SnakeView(Snake model){
        super(model, Images.getDefaultSnakeHead(), Images.getDefaultSnakeBody());
    }
}
