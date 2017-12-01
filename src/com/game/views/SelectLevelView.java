package com.game.views;

import com.game.classes.Defaults;
import com.game.classes.Game;
import com.game.classes.Images;
import com.game.classes.MapGUI;
import com.game.classes.exceptions.GameSerializableException;
import com.game.classes.exceptions.LevelBadSizeException;

import javax.swing.*;
import java.awt.*;


public class SelectLevelView extends JPanel {

    private MapGUI mapGUI;
    private Game game;
    private final int width = 600;
    private final int height = 500;

    public SelectLevelView(){
        setPreferredSize(new Dimension(width, height));
        JButton light  = getLightButton();
        add(light);
        JButton medium = getMediumButton();
        add(medium);
        JButton hard = getHardButton();
        add(hard);
    }

    private JButton getLightButton() {
        return MainMenu.getButton("Light Level",new Dimension(100,50),Color.lightGray,(e -> {
            try {
                this.game = Defaults.getEasyGame();
                //а здесь магическим образом надо высвечивать картинку
                Display display = new Display((MapGUI) game.getMap(), game);
                display.startGame();
            } catch (NoSuchMethodException | LevelBadSizeException e1) {
                e1.printStackTrace();
            }
        }));
    }

    private JButton getMediumButton() {
        return MainMenu.getButton("Medium Level",new Dimension(100,50),Color.lightGray,(e -> {
            try {
                this.game = Defaults.getMediumGame();
                Display display = new Display((MapGUI) game.getMap(), game);
                display.startGame();
            } catch (NoSuchMethodException | LevelBadSizeException e1) {
                e1.printStackTrace();
            }
        }));
    }

    private JButton getHardButton() {
        return MainMenu.getButton("Hard Level",new Dimension(100,50),Color.lightGray,(e -> {
            try {
                this.game = Defaults.getHardGame();
                Display display = new Display((MapGUI) game.getMap(), game);
                display.startGame();
            } catch (NoSuchMethodException | LevelBadSizeException e1) {
                e1.printStackTrace();
            }
        }));
    }
}
