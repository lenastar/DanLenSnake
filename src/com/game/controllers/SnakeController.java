package com.game.controllers;

import com.game.classes.enumerators.Directions;
import com.game.classes.interfaces.IController;
import com.game.models.Snake;

import java.util.HashMap;

public class SnakeController implements IController<Snake>{
    private final Snake Model;
    private HashMap<Integer, Runnable> Actions;

    public SnakeController(Snake model) throws NoSuchMethodException {
        Model = model;
        Actions = new HashMap<Integer, Runnable>();
        Actions.put((int)'W', () -> Model.setDirection(Directions.Up));
        Actions.put((int)'S', () -> Model.setDirection(Directions.Down));
        Actions.put((int)'A', () -> Model.setDirection(Directions.Left));
        Actions.put((int)'D', () -> Model.setDirection(Directions.Right));
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
