package com.levelConstructor.views;

import com.game.classes.exceptions.LevelBadSizeException;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame{
    private final int width = 600;
    private final int height = 600;
    private JScrollPane scrollPane;
    private ConstructorView constructorView;

    public MainMenu() throws LevelBadSizeException {
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        constructorView = new ConstructorView(30, 30 ,"Name", 30);
        getContentPane().add(constructorView);
        setJMenuBar(new ConstructorMenuBar(constructorView));
        setConstructorScrollPane();
        add(scrollPane);
        setVisible(true);
        pack();
    }

    public void setConstructorScrollPane(){
        scrollPane = new JScrollPane(constructorView);
        scrollPane.setSize(getPreferredSize());
    }
}
