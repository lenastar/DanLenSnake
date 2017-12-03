package com.levelConstructor.views;

import com.game.classes.SerializationUtil;
import com.game.classes.exceptions.GameSerializableException;
import com.game.models.Level;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ConstructorMenuBar extends JMenuBar{
    private final ConstructorView constructorView;

    public ConstructorMenuBar(ConstructorView constructorView){
        this.constructorView = constructorView;

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('f');
        add(fileMenu);
        //region add open item to file menu
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
        //endregion
        //region add save item to file menu
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
        //endregion
        //region add exit item to file menu
        JMenuItem exitFileMenuItem = new JMenuItem("Exit");
        exitFileMenuItem.setMnemonic('e');
        exitFileMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitFileMenuItem);
        //endregion

        JMenu toolsMenu = new JMenu("Tools");
        add(toolsMenu);
        //region add erase mode
        JMenuItem eraseMode = new JMenuItem("Set Erase Mode");
        eraseMode.addActionListener(e -> constructorView.setEraseMode());
        toolsMenu.add(eraseMode);
        //endregion
        //region add drawing mode
        JMenuItem drawingMode = new JMenuItem("Set Drawing Mode");
        drawingMode.addActionListener(e -> constructorView.setDrawingMode());
        toolsMenu.add(drawingMode);
        //endregion
        //region change size of canvas
        JMenuItem changeSizeMenuItem = new JMenuItem("Change Size");
        changeSizeMenuItem.addActionListener(e -> ResizeCanvasDialog.show(e1 -> {
            constructorView.
        }));
        toolsMenu.add(changeSizeMenuItem);
        //endregion
    }
}
