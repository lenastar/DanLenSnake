package com.game.views;

import com.game.classes.Game;
import com.game.classes.GameTimer;
import com.game.classes.Images;
import com.game.classes.MapGUI;
import com.game.classes.exceptions.LevelBadSizeException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {

    private final MapGUI mapGUI;
    private final Game game;

    public Display(MapGUI mapGUI,Game game){
        this.mapGUI = mapGUI;
        this.game = game;
    }

    public void startGame() throws LevelBadSizeException, NoSuchMethodException {
        JDialog dlg = new JDialog((JFrame) null, "Snake");
        dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        startGameAfterTimer(dlg);
        game.start();

        dlg.getContentPane().add(mapGUI);
        dlg.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                game.processKey(ev);
               MainMenu.processKey(ev, dlg, game);
            }
        });
        dlg.setVisible(true);
        dlg.pack();
        dlg.setResizable(false);
        dlg.setLocation(300, 200);

    }


}

