package com.game.views;



import com.game.models.Settings;

import javax.swing.*;
import java.awt.*;


public class SettingsView extends JPanel{
    private JCheckBox sound;
    private Settings model;
    private JCheckBox fullscreen;

    public SettingsView(Settings settings){
        setPreferredSize(new Dimension(300,200));
        setLayout(new BorderLayout());
        model = settings;
        sound = new JCheckBox("Sound");
        add(sound,BorderLayout.BEFORE_FIRST_LINE);
        sound.addItemListener(e -> model.setSoundOn(!model.isSoundOn()));
        fullscreen = new JCheckBox("Full screen");
        add(fullscreen,BorderLayout.LINE_START);
        fullscreen.addItemListener(e -> model.setFullScreen(!model.isFullScreen()));
    }
}
