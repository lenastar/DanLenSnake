package com.game.controllers;

import com.game.classes.Directions;
import com.game.classes.IController;
import com.game.models.Snake;
import com.game.views.SnakeView;

import java.lang.reflect.Method;
import java.util.HashMap;

public class SnakeController implements IController<Snake, SnakeView>{
    private Snake Model;
    private SnakeView View;
    private HashMap<Integer, Method> Actions;

    public SnakeController(Snake model, SnakeView view) throws NoSuchMethodException {
        Model = model;
        View = view;
        Actions = new HashMap<Integer, Method>();
        Actions.put((int)'w', SnakeController.class.getMethod("moveUp"));
        Actions.put((int)'s', SnakeController.class.getMethod("moveDown"));
        Actions.put((int)'a', SnakeController.class.getMethod("moveLeft"));
        Actions.put((int)'d', SnakeController.class.getMethod("moveRight"));
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
    public Method getAction(int key) {
        return Actions.get(key);
    }

    @Override
    public boolean keyExists(int key) {
        return Actions.containsKey(key);
    }

    public void moveUp() throws Exception
    {
        Model.setDirection(Directions.Up);
    }

    public void moveDown() throws Exception
    {
        Model.setDirection(Directions.Down);
    }

    public void moveLeft() throws Exception
    {
        Model.setDirection(Directions.Left);
    }

    public void moveRight() throws Exception
    {
        Model.setDirection(Directions.Right);
    }
}
