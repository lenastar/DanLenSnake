package com.game.views;

import com.game.classes.Context;
import com.game.classes.interfaces.IView;
import com.game.models.Level;

import java.awt.*;

public class LevelView implements IView<Context> {
    private final Level model;

    public LevelView(Level model) {
        this.model = model;
    }

    public Level getModel() {
        return model;
    }

    @Override
    public void paint(Context context) throws IndexOutOfBoundsException {
        for (Point point: model.getWalls()){
            context.map.drawPoint(context.g, point, Color.LIGHT_GRAY);
        }
    }
}
