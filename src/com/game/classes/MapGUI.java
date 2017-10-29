package com.game.classes;

import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.interfaces.IMap;
import com.game.classes.interfaces.IView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapGUI extends JPanel implements IMap {
    private final Level level;
    private final int cellSize;
    private ArrayList<IView> container;

    public MapGUI(int width, int height, int cellSize) throws LevelBadSizeException {
        this(width, height, cellSize, Level.getDefaultLevel(width, height));
    }

    public MapGUI(int width, int height, int cellSize, Level level) throws LevelBadSizeException {
        this.level = level;
        container = new ArrayList<>();
        Dimension dimension = new Dimension(width * cellSize, height * cellSize);
        setPreferredSize(dimension);
        this.cellSize = cellSize;
        setBackground(Color.WHITE);
    }

    public void addView(IView view) {
        container.add(view);
    }

    public ArrayList<IView> getViews() {
        return container;
    }

    @Override
    public void paint(){
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

        for (Point point: level.getWalls()){
            drawPoint(g, point, Color.LIGHT_GRAY);
        }

        for (IView view: container) {
            view.paint(new Context(this, g));
        }
    }

    public void drawPoint(Graphics g, Point point, Color color) throws IndexOutOfBoundsException{
        if (point.x < 0
                || point.y < 0
                || point.x >= getLevel().getWidth()
                || point.y >= getLevel().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        g.setColor(color);
        g.fillRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
    }

    public void drawImagePoint(Graphics g, Point point, Image image) throws IndexOutOfBoundsException{
        if (point.x < 0
                || point.y < 0
                || point.x >= getLevel().getWidth()
                || point.y >= getLevel().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        g.drawImage(image,point.x * cellSize, point.y * cellSize, cellSize, cellSize,null);
    }

}
