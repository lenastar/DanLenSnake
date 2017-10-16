package com.game.views;

import com.game.classes.Game;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.LevelBadSizeException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {

    private int Width = 600;
    private int Height = 500;
    private int WidthButton = 100;
    private int HeightButton = 50;
    public static void main(String[] args) throws IOException {
        Display app = new Display();
    }

    public Display() throws IOException {

        JFrame frame = new JFrame("Snake");
        JPanelWithBackground panel = new JPanelWithBackground("src/com/game/resources/images/images.jpg");
        frame.setVisible(true);

        //Image img = new ImageIcon(Application.class.getResource("C:\\Users\\Елена\\IdeaProjects\\DanLenSnake\\src\\com\\game\\resourses\\images\\preview.gif")).getImage();
      //  frame.setIconImage(img);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(Width,Height));


        final JButton start = new JButton();
        start.setText("Start");
        start.setPreferredSize(new Dimension(WidthButton,HeightButton));
        start.setBackground(Color.lightGray);
        panel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    try {
                        startGame();
                    } catch (LevelBadSizeException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchMethodException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        final JButton help = new JButton();
        help.setText("Help");
        help.setPreferredSize(new Dimension(WidthButton,HeightButton));
        help.setBackground(Color.lightGray);
        panel.add(help);
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {JOptionPane.showMessageDialog(frame," It is game \"Snake\".\n Your task is managing the keys W, A, S, D to control the snake and collect food so that your snake has reached its maximum size.\n The snake will die if it bites itself or hits the boundaries of the playing field.\n Good luck!");}
            }
        });

        final JButton exit = new JButton();
        exit.setText("Exit");
        exit.setPreferredSize(new Dimension(WidthButton,HeightButton));
        exit.setBackground(Color.lightGray);
        panel.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        frame.setResizable(false);
        frame.getContentPane().add(panel);
        frame.pack();

    }

    class JPanelWithBackground extends JPanel {

        private Image backgroundImage;

        public JPanelWithBackground(String fileName) throws IOException {
            backgroundImage = ImageIO.read(new File(fileName));

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage,0,0,600,500,this);
        }
    }

    public void startGame() throws LevelBadSizeException, NoSuchMethodException {
        JDialog dlg = new JDialog((JFrame) null, "Snake");
        dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MapGUI map = new MapGUI(20,10,30);
        Game game = new Game(map);
        game.addInstance(Model.createSnake(new Point(5,5), 5, Direction.Down));
        dlg.getContentPane().add(map);
        game.start();
        dlg.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                game.processKey(ev);
                if (ev.getKeyCode() == KeyEvent.VK_ESCAPE){
                    if (game.isRunning()) {
                        game.stop();
                    } else {
                        game.start();
                    }
                }
            }
        });
        dlg.setVisible(true);
        dlg.pack();
        dlg.setResizable(true);
        dlg.setLocation(300, 200);
        dlg.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                game.stop();
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
    }
}

