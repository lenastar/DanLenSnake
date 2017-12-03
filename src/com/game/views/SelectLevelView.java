package com.game.views;


import com.game.classes.*;
import com.game.classes.exceptions.LevelBadSizeException;
import javax.swing.*;
import java.awt.*;

public class SelectLevelView extends JFrame {

    private Game game;
    private final int width = 600;
    private final int height = 500;


    public SelectLevelView(){
        JPanel panel = new JPanel();
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(null);
        makeButtons(panel);
        setVisible(true);
        getContentPane().add(panel);
        pack();
    }

    public void setGame(Game game ){
        this.game = game;
    }

    private JButton getEasyButton() {
        return MainMenu.getButton("Light Level",new Dimension(100,50),Color.pink,( e -> {
            setGame(Defaults.getEasyGame());
            Image image = Images.getEasy();
            setComponentOnBack(image);
        }
        ));
    }

    private JButton getMediumButton() {
        return MainMenu.getButton("Medium Level",new Dimension(100,50),Color.pink,(e -> {
            setGame(Defaults.getMediumGame());
            Image image = Images.getMedium();
            setComponentOnBack(image);
        }
        ));
    }

    private JButton getHardButton() {
        return MainMenu.getButton("Hard Level",new Dimension(100,50),Color.pink,(e ->{
            setGame(Defaults.getHardGame());
            Image image = Images.getHard();
            setComponentOnBack(image);
    }
        ));
    }

    private JButton getRunButton(){
        return MainMenu.getButton("Run", new Dimension(100, 50), Color.PINK, (e -> {
            try {
                Display display = new Display((MapGUI) game.getMap(), game);
                display.startGame();
            } catch (LevelBadSizeException | NoSuchMethodException e1){
                e1.printStackTrace();
            }
        }));
    }

    private void makeButtons(JPanel panel){
        JButton easy = getEasyButton();
        easy.setBounds(100,20,100,50);
        panel.add(easy);
        JButton medium = getMediumButton();
        medium.setBounds(250,20,100,50);
        panel.add(medium);
        JButton hard = getHardButton();
        hard.setBounds(400,20,100,50);
        panel.add(hard);
        JButton run = getRunButton();
        run.setBounds(250,400,100,50);
        panel.add(run);
    }

    private void setComponentOnBack(Image image){
        JPanel panel = new JPanelWithBackground(image,width,height);
        this.getContentPane().add(panel,1);
        this.setComponentZOrder(panel,1);
        this.setVisible(true);
    }
}
