package com.game.models;

import com.game.classes.enumerators.Directions;

import java.awt.*;
import java.util.ArrayList;

public class Snake{
    private ArrayList<Point> Segments;
    private Directions Direction;

    public Snake(Point head, int length, Directions direction)
    {
        Direction = direction;
        Segments = new ArrayList<Point>();
        Segments.add(head);
        for (int index = 1; index < length; index++)
        {
            switch (direction)
            {
                case Left:
                    Segments.add(0, new Point(head.x, head.y + index));
                    break;
                case Right:
                    Segments.add(0, new Point(head.x, head.y - index));
                    break;
                case Up:
                    Segments.add(0, new Point(head.x + index, head.y));
                    break;
                case Down:
                    Segments.add(0, new Point(head.x - index, head.y));
                    break;
            }
        }
    }

    public Snake(Point head, int length)
    {
        this(head, length, Directions.Left);
    }

    public void move(Directions direction) throws Exception
    {
        if (direction == Directions.Down && Direction == Directions.Up ||
                Direction == Directions.Down && direction == Directions.Up ||
                direction == Directions.Left && Direction == Directions.Right ||
                        Direction == Directions.Left && direction == Directions.Right)
        {
            throw new Exception("Snake can't move in oppsite direction");
        }
        Direction = direction;

        for (int index = 1; index < Segments.size(); index++)
        {
            Segments.set(index - 1, Segments.get(index));
        }

        Point head = new Point(getHead());

        switch (direction){
            case Down:
                head.translate(1, 0);
                break;
            case Up:
                head.translate(-1, 0);
                break;
            case Left:
                head.translate(0, -1);
                break;
            case Right:
                head.translate(0, 1);
                break;
        }
        Segments.set(Segments.size() - 1, head);
    }

    public Point getHead()
    {
        return Segments.get(Segments.size() - 1);
    }

    public void grow(int length, Point location)
    {
        for (int index = 0; index < length; index++)
        {
            Segments.add(0, new Point(location));
        }
    }

    public boolean isSnake(Point point)
    {
        return isBody(point) || isHead(point);
    }

    public boolean isBody(Point point)
    {
        for (int index = 0; index < Segments.size() - 1; index++)
        {
            if (Segments.get(index).equals(point))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isHead(Point point)
    {
        return point.equals(getHead());
    }

    public Directions getDirection()
    {
        return Direction;
    }

    public void setDirection(Directions direction)
    {
        Direction = direction;
    }

    public ArrayList<Point> getSegments() {
        return Segments;
    }

    public int getLength()
    {
        return Segments.size();
    }
}
