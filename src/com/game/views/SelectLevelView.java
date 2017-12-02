package com.game.views;


import com.game.classes.Defaults;
import com.game.classes.Game;

import com.game.classes.MapGUI;

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
        setLayout(new BorderLayout(2,2));
        JButton easy  = getEasyButton();
        add(easy,BorderLayout.LINE_START);
        JButton medium = getMediumButton();
        add(medium,BorderLayout.CENTER);
        JButton hard = getHardButton();
        add(hard,BorderLayout.LINE_END);
        JButton run = getRunButton();
        add(run, BorderLayout.PAGE_START);
    }

    public void setGame(Game game ){
        this.game = game;
    }
    private JButton getEasyButton() {
        return MainMenu.getButton("Light Level",new Dimension(100,50),Color.lightGray,(e ->
            setGame(Defaults.getEasyGame())
        ));
    }

    private JButton getMediumButton() {
        return MainMenu.getButton("Medium Level",new Dimension(100,50),Color.lightGray,(e ->
            setGame(Defaults.getMediumGame())
        ));
    }

    private JButton getHardButton() {
        return MainMenu.getButton("Hard Level",new Dimension(100,50),Color.lightGray,(e ->
            setGame(Defaults.getHardGame())
        ));
    }

    private JButton getRunButton(){
        return MainMenu.getButton("Run", new Dimension(100, 50), Color.lightGray, (e -> {
            try {
                Display display = new Display((MapGUI) game.getMap(), game);
                display.startGame();
            } catch (LevelBadSizeException | NoSuchMethodException e1){
                e1.printStackTrace();
            }
        }));
    }
}
