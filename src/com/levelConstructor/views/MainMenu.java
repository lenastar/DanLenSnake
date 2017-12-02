package com.levelConstructor.views;

import com.game.classes.exceptions.LevelBadSizeException;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame{
    private final int width = 600;
    private final int height = 600;

    public MainMenu() throws LevelBadSizeException {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setResizable(false);
        ConstructorView constructorView = new ConstructorView(30, 30 ,"Name", 30);
        getContentPane().add(constructorView);
        setJMenuBar(new ConstructorMenuBar(constructorView));
        JScrollPane scrollPane = new JScrollPane(constructorView);
        scrollPane.setPreferredSize(getPreferredSize());
        add(scrollPane);
        setVisible(true);
        pack();
    }


}
