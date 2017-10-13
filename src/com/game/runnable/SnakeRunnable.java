package com.game.runnable;

import com.game.classes.Game;
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
    public boolean run(Game game) throws Exception {
        model.move(model.getDirection());
        if (game.getFoodManager().isFood(model.getHead()))
        {
            model.grow(game
                    .getFoodManager()
                    .getFood(model.getHead()).getScores(), model.getHead());
            game.getFoodManager().removeFood(model.getHead());
        }
        if (game.getMap().getLevel().isCollision(model.getHead()) ||
                model.isBody(model.getHead())){
            return false;
        }
        return true;
    }
}
