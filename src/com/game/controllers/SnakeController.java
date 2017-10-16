package com.game.controllers;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IController;
import com.game.models.Snake;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class SnakeController implements IController<Snake>{
    private final Snake Model;
    private HashMap<Integer, Runnable> Actions;

    public SnakeController(Snake model) throws NoSuchMethodException {
        Model = model;
        Actions = new HashMap<Integer, Runnable>();
        Actions.put(KeyEvent.VK_UP, () -> {
            try {
                Model.setDirection(Direction.Up);
            } catch (SnakeOppositeMoveException e) {

            }
        });
        Actions.put(KeyEvent.VK_DOWN, () -> {
            try {
                Model.setDirection(Direction.Down);
            } catch (SnakeOppositeMoveException e) {

            }
        });
        Actions.put(KeyEvent.VK_LEFT, () -> {
            try {
                Model.setDirection(Direction.Left);
            } catch (SnakeOppositeMoveException e) {

            }
        });
        Actions.put(KeyEvent.VK_RIGHT, () -> {
            try {
                Model.setDirection(Direction.Right);
            } catch (SnakeOppositeMoveException e) {

            }
        });
    }

    @Override
    public Snake getModel() {
        return Model;
    }

    @Override
    public void runAction(int key) {
        Actions.get(key).run();
    }

    @Override
    public boolean keyExists(int key) {
        return Actions.containsKey(key);
    }
}
