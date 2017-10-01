package com.game.controllers;

import com.game.classes.IController;
import com.game.models.Food;
import com.game.views.FoodView;

import java.lang.reflect.Method;

public class FoodController implements IController<Food, FoodView>{
    private Food Model;
    private FoodView View;

    public FoodController(Food model, FoodView view)
    {
        Model = model;
        View = view;
    }

    @Override
    public Food getModel() {
        return Model;
    }

    @Override
    public FoodView getView() {
        return View;
    }

    @Override
    public Method getAction(int key) {
        return null;
    }

    @Override
    public boolean keyExists(int key) {
        return false;
    }
}
