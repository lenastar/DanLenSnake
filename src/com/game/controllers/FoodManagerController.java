package com.game.controllers;

import com.game.classes.FoodManager;
import com.game.classes.IController;
import com.game.views.FoodManagerView;

public class FoodManagerController implements IController<FoodManager, FoodManagerView>{
    private final FoodManager Model;
    private final FoodManagerView View;

    public FoodManagerController(FoodManager model, FoodManagerView view) {
        Model = model;
        View = view;
    }

    @Override
    public FoodManager getModel() {
        return Model;
    }

    @Override
    public FoodManagerView getView() {
        return View;
    }

    @Override
    public void runAction(int key) { }

    @Override
    public boolean keyExists(int key) {
        return false;
    }
}
