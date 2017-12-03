package com.game.views;

import com.game.models.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;



public class SettingsView extends JPanel {
    private JCheckBox sound;
    private Settings model;

    public SettingsView(Settings settings) {
        setPreferredSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        model = settings;
        sound = new JCheckBox("Sound");
        add(sound, BorderLayout.BEFORE_FIRST_LINE);
        sound.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                model.setSoundOn();
                 } else {
                model.setSoundOff();
                     }
            }
        );

    }
}
