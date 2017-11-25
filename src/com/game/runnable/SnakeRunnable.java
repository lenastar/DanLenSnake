package com.game.runnable;

import com.game.classes.Game;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IRunnable;
import com.game.models.Snake;

import java.awt.*;

public class SnakeRunnable implements IRunnable{
    private final Snake model;

    public SnakeRunnable(Snake model){
        this.model = model;
    }

    public Snake getModel() {
        return model;
    }

    @Override
    public boolean run(Game game){
        model.move();
        //region: Make move cyclically
        int x = model.getHead().x;
        int y = model.getHead().y;
        int width = game.getMap().getDimension().width;
        int height = game.getMap().getDimension().height;
        model.getHead().move((x + width) % width, (y + height) % height);
        //endregion
        return game.getContainerModels()
                        .stream()
                        .allMatch(m->m.snakeIsAliveAfterCollision(model));
    }
}
