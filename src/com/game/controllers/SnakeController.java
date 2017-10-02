package com.game.controllers;

import com.game.classes.Directions;
import com.game.classes.IController;
import com.game.models.Snake;
import com.game.views.SnakeView;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.HashMap;

public class SnakeController implements IController<Snake, SnakeView>{
    private final Snake Model;
    private final SnakeView View;
    private HashMap<Integer, Runnable> Actions;

    public SnakeController(Snake model, SnakeView view) throws NoSuchMethodException {
        Model = model;
        View = view;
        Actions = new HashMap<Integer, Runnable>();
        Actions.put((int)'w', () -> Model.setDirection(Directions.Up));
        Actions.put((int)'s', () -> Model.setDirection(Directions.Down));
        Actions.put((int)'a', () -> Model.setDirection(Directions.Left));
        Actions.put((int)'d', () -> Model.setDirection(Directions.Right));
    }

    @Override
    public Snake getModel() {
        return Model;
    }

    @Override
    public SnakeView getView() {
        return View;
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
