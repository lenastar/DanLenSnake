package com.game.classes;

import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.interfaces.IMap;
import com.game.classes.interfaces.IView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapGUI extends JPanel implements IMap {
    private final Level level;
    private final int CELL_SIZE;
    private ArrayList<IView> container;
    private Random random = new Random();

    public MapGUI(int width, int height, int cellSize) throws LevelBadSizeException {
        level = Level.getDefaultLevel(width, height);
        container = new ArrayList<>();
        Dimension dimension = new Dimension(width * cellSize, height * cellSize);
        setPreferredSize(dimension);
        this.CELL_SIZE = cellSize;
        setBackground(Color.WHITE);
    }

    public void addView(IView view) {
        container.add(view);
    }

    @Override
    public void paint() {
        this.repaint();
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /*g2.setColor(Color.DARK_GRAY);
        for (int j = 0; j < level.getHeight(); j++) {
            for (int i = 0; i < level.getWidth(); i++) {
                g2.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }*/
        for (Point point: level.getWalls()){
            drawPoint(g, point, Color.LIGHT_GRAY);
        }

        for (IView view: container) {
            view.paint(new Context(this, g));
        }
    }

    public void drawPoint(Graphics g, Point point, Color color) {
        g.setColor(color);
        g.fillRect(point.y * CELL_SIZE, point.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(point.y * CELL_SIZE, point.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void drawImagePoint(Graphics g, Point point, Image image){
       // g.fillRect(point.y * CELL_SIZE, point.x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.drawImage(image,point.y * CELL_SIZE, point.x * CELL_SIZE, CELL_SIZE, CELL_SIZE,null);
    }
}
