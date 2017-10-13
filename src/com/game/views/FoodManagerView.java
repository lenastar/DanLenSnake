package com.game.views;

import com.game.classes.Context;
import com.game.models.FoodManager;
import com.game.classes.interfaces.IView;

public class FoodManagerView implements IView<FoodManager, Context> {
    private final FoodManager Model;

    public FoodManagerView(FoodManager model) {
        Model = model;
    }

    @Override
    public FoodManager getModel() {
        return Model;
    }

    @Override
    public void paint(Context context) throws IndexOutOfBoundsException {
        //TODO:implement method
    }
}
