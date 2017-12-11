package com.levelConstructor.views;

import com.game.classes.SerializationUtil;
import com.game.classes.exceptions.GameSerializableException;
import com.game.models.Level;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ConstructorMenuBar extends JMenuBar{
    private final ConstructorView constructorView;
    private final JMenu fileMenu;
    private final JMenu toolsMenu;

    public ConstructorMenuBar(ConstructorView constructorView){
        this.constructorView = constructorView;
        fileMenu =new JMenu("File");
        fileMenu.setMnemonic('f');
        add(fileMenu);
        addOpenFileMenuItem();
        addSaveMenuItem();
        addExitFileMenuItem();

        toolsMenu = new JMenu("Tools");
        add(toolsMenu);
        addEraseMode();
        addDrawingMode();
        addChangeSizeMode();
    }
    private void addOpenFileMenuItem(){
        JMenuItem openFileMenuItem = new JMenuItem("Open");
        openFileMenuItem.setMnemonic('o');
        openFileMenuItem.addActionListener(e -> {
            final JFileChooser chooser = new JFileChooser();
            int value = chooser.showOpenDialog(this);
            if (value == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                try {
                    Level level = Level.get(file.getAbsolutePath());
                    constructorView.changeLevel(level);
                } catch (GameSerializableException e1) {
                    e1.printStackTrace();
                }
            }
        });
        fileMenu.add(openFileMenuItem);
    }
    private void addSaveMenuItem(){
        JMenuItem saveFileMenuItem = new JMenuItem("Save");
        saveFileMenuItem.setMnemonic('s');
        saveFileMenuItem.addActionListener(e -> {
            final JFileChooser chooser = new JFileChooser();
            int value = chooser.showOpenDialog(this);
            if (value == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                constructorView.getConstructor().save(file.getAbsolutePath());
            }
        });
        fileMenu.add(saveFileMenuItem);
    }
    private void addExitFileMenuItem(){
        JMenuItem exitFileMenuItem = new JMenuItem("Exit");
        exitFileMenuItem.setMnemonic('e');
        exitFileMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitFileMenuItem);
    }
    private void addEraseMode(){
        JMenuItem eraseMode = new JMenuItem("Set Erase Mode");
        eraseMode.addActionListener(e -> constructorView.setEraseMode());
        toolsMenu.add(eraseMode);
    }
    private void addDrawingMode(){
        JMenuItem drawingMode = new JMenuItem("Set Drawing Mode");
        drawingMode.addActionListener(e -> constructorView.setDrawingMode());
        toolsMenu.add(drawingMode);
    }
    private void addChangeSizeMode(){
        JMenuItem changeSizeMenuItem = new JMenuItem("Change Size");
        changeSizeMenuItem.addActionListener(e -> ResizeCanvasDialog.show(constructorView));
        toolsMenu.add(changeSizeMenuItem);
    }
}
