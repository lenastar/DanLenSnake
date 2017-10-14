package com.game.views;

import javafx.application.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {

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
        frame.setPreferredSize(new Dimension(600,500));


        final JButton start = new JButton();
        start.setText("Start");
        start.setPreferredSize(new Dimension(100,50));
        start.setContentAreaFilled(false);
        panel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {System.out.println("start");}
            }
        });

        final JButton help = new JButton();
        help.setText("Help");
        help.setPreferredSize(new Dimension(100,50));
        help.setContentAreaFilled(false);
        panel.add(help);
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {System.out.println("help");}
            }
        });


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
}

