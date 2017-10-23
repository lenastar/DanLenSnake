package com.game.classes;

import com.game.classes.interfaces.IController;
import com.game.classes.interfaces.IModel;
import com.game.classes.interfaces.IRunnable;
import com.game.classes.interfaces.IView;

public class Instance<
        TModel extends IModel,
        TView extends IView,
        TController extends IController,
        TRunnable extends IRunnable> {
    private final TModel model;
    private final TView view;
    private final TController controller;
    private  final TRunnable runnable;

    public Instance(TModel model, TView view, TController controller, TRunnable runnable){
        this.model = model;
        this.view = view;
        this.controller = controller;
        this.runnable = runnable;
    }

    public TController getController() {
        return controller;
    }

    public TModel getModel() {
        return model;
    }

    public TRunnable getRunnable() {
        return runnable;
    }

    public TView getView() {
        return view;
    }
}
