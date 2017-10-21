package com.game.classes;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GameTimer{

    private JLabel timeLabel;
    private Timer timer;
    private JButton start;
    private final JDialog component;
    private final Game game;

    public GameTimer(JDialog component, Game game) {
        this.component = component;
        this.game = game;

        this.component.setLayout(new FlowLayout());
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 80));
        timer = new Timer(1000, new TimerTick());
    }

    public void start(){
        component.add(timeLabel);
        component.validate();
        component.repaint();
        timer.start();
    }

    class TimerTick implements ActionListener {

        int countdown = 3;

        @Override
        public void actionPerformed(ActionEvent e) {
            countdown--;
            timeLabel.setText(String.valueOf(countdown));
            if (countdown == 0) {
                timer.stop();
                timeLabel.setText("");
                game.start();
            }
        }

    }
}