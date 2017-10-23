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
    public boolean run(Game game) throws SnakeOppositeMoveException {
        model.move();
        if (game.getMap().getLevel().isCollision(model.getHead()) ||
                model.isBodyCollisionWith(model.getHead())) {
            return false;
        }
        if (game.getFoodManager().isCollisionWith(model.getHead())) {
            model.grow(game
                    .getFoodManager()
                    .getFood(model.getHead()).getScores());
            game.getFoodManager().removeFood(model.getHead());
        }
        return true;
    }
}
