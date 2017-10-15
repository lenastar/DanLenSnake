package com.game.views;

import com.game.classes.Game;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Food;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
        JPanelWithBackground panel = new JPanelWithBackground("C:\\Users\\Елена\\IdeaProjects\\DanLenSnake\\src\\com\\game\\resourses\\images\\images.jpg");
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
                {frame.setVisible(false);}
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
        MapGUI map = new MapGUI(30,30,20);
        Game sn = new Game(map);
        sn.addFood(new Food(new Point(10,10),5));
        sn.addInstance(Model.createSnake(new Point(5,5), 5));
        dlg.getContentPane().add(map);
        sn.start();
        dlg.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                sn.processKey(ev);
            }
        });
        dlg.setVisible(true);
        dlg.pack();
        dlg.setResizable(true);
        dlg.setLocation(300, 200);
}
}

