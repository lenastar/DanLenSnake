package com.game.controllers;

import com.game.classes.PointAndDirectionUtils;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IController;
import com.game.models.Snake;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class SnakeController implements IController{
    private final Snake model;
    private HashMap<Integer, Runnable> actions;

    public SnakeController(Snake model) throws NoSuchMethodException {
        this.model = model;
        actions = new HashMap<>();
        for (Integer keyInteger: PointAndDirectionUtils.keyValues()){
            actions.put(keyInteger, () -> {
                try {
                    this.model.setDirection(PointAndDirectionUtils.getDirection(keyInteger));
                    //    this.model.
                } catch (SnakeOppositeMoveException e) {

                }
            });
        }
    }

    public Snake getModel() {
        return model;
    }

    @Override
    public void runAction(int key){
        actions.get(key).run();
    }

    @Override
    public boolean keyExists(int key) {
        return actions.containsKey(key);
    }
}
