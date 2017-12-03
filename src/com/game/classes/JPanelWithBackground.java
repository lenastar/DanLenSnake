package com.game.classes;


import javax.swing.*;
import java.awt.*;

public class JPanelWithBackground extends JPanel {

    private Image backgroundImage;
    private int Width;
    private int Height;

    public JPanelWithBackground(Image image, int width, int height) {
        backgroundImage = image;
        Width = width;
        Height = height;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0, Width, Height,this);
    }
}