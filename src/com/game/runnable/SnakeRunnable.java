package com.game.runnable;

import com.game.classes.Game;
import com.game.classes.PointAndDirectionUtils;
import com.game.classes.interfaces.IRunnable;
import com.game.models.Snake;

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
        moveCyclically(game);
        return game.getContainerModels()
                        .stream()
                        .allMatch(m->m.snakeIsAliveAfterCollision(model));
    }

    public void moveCyclically(Game game){
        model.move();
        int x = model.getHead().x;
        int y = model.getHead().y;
        int width = game.getMap().getDimension().width;
        int height = game.getMap().getDimension().height;
        model.getHead().setLocation(PointAndDirectionUtils.moveCyclically(x, y, width, height));
    }
}
