package com.game.controllers;

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
        actions.put(KeyEvent.VK_UP, () -> {
            try {
                this.model.setDirection(Direction.Up);
            } catch (SnakeOppositeMoveException e) {

            }
        });
        actions.put(KeyEvent.VK_DOWN, () -> {
            try {
                this.model.setDirection(Direction.Down);
            } catch (SnakeOppositeMoveException e) {

            }
        });
        actions.put(KeyEvent.VK_LEFT, () -> {
            try {
                this.model.setDirection(Direction.Left);
            } catch (SnakeOppositeMoveException e) {

            }
        });
        actions.put(KeyEvent.VK_RIGHT, () -> {
            try {
                this.model.setDirection(Direction.Right);
            } catch (SnakeOppositeMoveException e) {

            }
        });
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
