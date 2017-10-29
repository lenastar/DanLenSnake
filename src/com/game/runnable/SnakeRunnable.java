package com.game.runnable;

import com.game.classes.Game;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IRunnable;
import com.game.models.Snake;

public class SnakeRunnable implements IRunnable<Snake>{
    private final Snake model;

    public SnakeRunnable(Snake model){
        this.model = model;
    }

    @Override
    public Snake getModel() {
        return model;
    }

    @Override
    public boolean run(Game game){
        model.move();
        return game.getMap().getLevel().snakeIsAliveAfterCollision(model)&&
                game.getContainerModels()
                        .stream()
                        .allMatch(m->m.snakeIsAliveAfterCollision(model));


    }
}
