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

    public SelectLevelView(MapGUI mapGUI, Game game){
        this.mapGUI = mapGUI;
        this.game = game;
        setPreferredSize(new Dimension(600,500));
        JButton light  = getLightButton();
        add(light);
        JButton medium = getMediumButton();
        add(medium);
        JButton hard = getHardButton();
        add(hard);
        JButton start = getStartButton();
        //хотела сделать её внизу, не успеваю
        add(start);
    }

    private JButton getStartButton() {
        return MainMenu.getButton("Start!",new Dimension(100,50),Color.lightGray,(e -> {
            Display display = new Display(mapGUI, game);
            try {
                display.startGame();
            } catch (LevelBadSizeException | NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        }));
    }

    private JButton getLightButton() {
        return MainMenu.getButton("Light Level",new Dimension(100,50),Color.lightGray,(e -> {
            try {
                this.game = Defaults.getEasyGame();
                //а здесь магическим образом надо высвечивать картинку
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        }));
    }

    private JButton getMediumButton() {
        return MainMenu.getButton("Medium Level",new Dimension(100,50),Color.lightGray,(e -> {
            try {
                this.game = Defaults.getMediumGame();
            } catch (NoSuchMethodException | GameSerializableException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }));
    }

    private JButton getHardButton() {
        return MainMenu.getButton("Hard Level",new Dimension(100,50),Color.lightGray,(e -> {
            try {
                this.game = Defaults.getHardGame();
            } catch (NoSuchMethodException | GameSerializableException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }));
    }
}
