package com.game.views;

import javax.swing.*;
import java.awt.*;
import com.game.classes.Game;
import com.game.classes.GameTimer;
import com.game.classes.Images;
import com.game.classes.MapGUI;
import com.game.models.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends  JFrame{
        private final int Width = 600;
        private final int Height = 500;
        private final int WidthButton = 100;
        private final int HeightButton = 50;
        private final MapGUI mapGUI;
        private final Game game;
        private final Settings settings;

        public MainMenu(MapGUI mapGUI,Game game) throws IOException {
            this.mapGUI = mapGUI;
            this.game = game;
            settings = new Settings();
            JFrame frame = new JFrame("Snake");
            JPanelWithBackground panel = new JPanelWithBackground(Images.getBackground());
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(Width,Height));

            final JButton start = getStartButton();
            panel.add(start);

            final JButton help = getHelpButton(frame);
            panel.add(help);

            final JButton table = getTableButton(frame);
            panel.add(table);

            final JButton settings = getSettingsButton(frame);
            panel.add(settings);

            final JButton exit = getExitButton(frame);
            panel.add(exit);

            frame.setResizable(false);
            frame.getContentPane().add(panel);
            frame.pack();

        }

    private JButton getSettingsButton(JFrame frame) {
        return getButton("Settings", new Dimension(WidthButton,HeightButton),Color.lightGray, (ActionEvent e) ->{
            SettingsView view = new SettingsView(settings);
            JDialog dlg = new JDialog((JFrame) null, "Settings");
            dlg.setPreferredSize(new Dimension(300,200));
            dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dlg.getContentPane().add(view);
            dlg.setVisible(true);
            dlg.pack();
            dlg.setLocation(300, 200);
        });
    }

    private JButton getTableButton(JFrame frame) {
            return getButton("Records", new Dimension(WidthButton,HeightButton),Color.lightGray,e -> {
                        try{
                            StringBuilder output = new StringBuilder();
                            Scanner s = new Scanner(game.getHighscoreTable().toString());
                            s.useDelimiter(",\\s*");
                            while (s.hasNext()){
                                output.append(s.next());
                                output.append(" \n");
                            }
                            output.delete(0,1);
                            output.delete(output.length()-3,output.length()-2);
                            JOptionPane.showMessageDialog(frame,output);
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();

                        }
                    }
            );
    }

    public JButton getStartButton(){
            return getButton("Start",
                    new Dimension(WidthButton,HeightButton),
                    Color.lightGray,
                    e -> {
                        {
                            try {
                                SelectLevelView level = new SelectLevelView(mapGUI,game);
                                JDialog dlg = new JDialog((JFrame) null, "Settings");
                                dlg.setPreferredSize(new Dimension(Width,Height));
                                dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                dlg.getContentPane().add(level);
                                dlg.setVisible(true);
                                dlg.pack();
                                dlg.setLocation(300, 200);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
            );
        }

        public JButton getHelpButton(JFrame frame){
            return getButton("Help",
                    new Dimension(WidthButton,HeightButton),
                    Color.lightGray,
                    e -> JOptionPane.showMessageDialog(frame," It is game \"Snake\".\n " +
                            "Your task is managing the keys W,A,S,D to control the snake and collect food so " +
                            "that your snake has reached its maximum size.\n " +
                            "The snake will die if it bites itself or hits the boundaries of the playing field.\n " +
                            "Good luck!")
            );

        }

        public JButton getExitButton(JFrame frame){
            return getButton("Exit",
                    new Dimension(WidthButton,HeightButton),
                    Color.lightGray,
                    e -> frame.dispose()
            );
        }

    public static JButton getButton(String text, Dimension dimension, Color background, ActionListener listener){
        JButton button = new JButton();
        button.setText(text);
        button.setPreferredSize(dimension);
        button.setBackground(background);
        button.addActionListener(listener);
        return button;
    }

        public static void processKey(KeyEvent event, JDialog dialog, Game game){
            if (event.getKeyCode() == KeyEvent.VK_ESCAPE){
                game.stop();
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(dialog,
                        "Do you want to exit?",
                        "Snake",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                if (n == 0){
                    dialog.dispose();
                }
                else {
                    game.start();
                }
            }
            if (event.getKeyCode() == KeyEvent.VK_SPACE){
                if (game.isRunning()){
                    game.stop();
                }else{
                    game.start();
                }
            }
        }

        //  public void startGameAfterTimer(JDialog component){
        //      GameTimer gameTimer = new GameTimer(component, game);
        //     gameTimer.start();
        // }


        class JPanelWithBackground extends JPanel {

            private Image backgroundImage;

            public JPanelWithBackground(Image image) {
                backgroundImage = image;
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage,0,0,Width, Height,this);
            }
        }

    }


