package com.game.views;

import com.game.classes.Cheat;
import com.game.classes.Game;
import com.game.classes.MapGUI;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.exceptions.PathNotFoundException;
import com.game.controllers.DisplayController;
import com.game.models.Level;
import com.game.models.Settings;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Display extends JFrame {

    private final MapGUI mapGUI;
    private final Game game;
    private final Cheat cheat;

    public Display(MapGUI mapGUI,Game game){
        this.mapGUI = mapGUI;
        this.game = game;
        this.cheat = new Cheat(game);
    }

    public void startGame() throws LevelBadSizeException, NoSuchMethodException {
        JDialog dlg = new JDialog((JFrame) null, "Snake");
        dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        game.start();
        dlg.getContentPane().add(mapGUI);
        dlg.addKeyListener(new DisplayController(game, cheat));
        dlg.setVisible(true);
        dlg.pack();
        dlg.setResizable(false);
        dlg.setLocation(0, 0);
    }


}

