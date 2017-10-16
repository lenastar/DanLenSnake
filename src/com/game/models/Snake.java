package com.game.models;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IModel;

import java.awt.*;
import java.util.ArrayList;

public class Snake implements IModel{
    private ArrayList<Point> segments;
    private Direction direction;

    public Snake(Point head, int length, Direction direction)
    {
        this.direction = direction;
        segments = new ArrayList<Point>();
        segments.add(head);
        for (int index = 1; index < length; index++)
        {
            Point dirPoint = direction.opposite().getPoint();
            segments.add(0,
                    new Point(head.x + dirPoint.x * index,head.y + dirPoint.y * index));
        }
    }

    public Snake(Point head, int length)
    {
        this(head, length, Direction.Left);
    }

    public void move(Direction direction) throws SnakeOppositeMoveException
    {
        setDirection(direction);
        for (int index = 1; index < segments.size(); index++)
        {
            segments.set(index - 1, segments.get(index));
        }

        Point head = new Point(getHead());

        head.translate(direction.getPoint().x, direction.getPoint().y);
        segments.set(segments.size() - 1, head);
    }

    public Point getHead()
    {
        return segments.get(segments.size() - 1);
    }

    public void grow(int length, Point location)
    {
        for (int index = 0; index < length; index++)
        {
            segments.add(0, new Point(location));
        }
    }

    public boolean isCollisionWith(Point point)
    {
        return isBodyCollisionWith(point) || isHeadCollisionWith(point);
    }

    public boolean isBodyCollisionWith(Point point)
    {
        for (int index = 0; index < segments.size() - 1; index++)
        {
            if (segments.get(index).equals(point))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isHeadCollisionWith(Point point)
    {
        return point.equals(getHead());
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction) throws SnakeOppositeMoveException {
        if (this.direction.opposite() == direction){
            throw new SnakeOppositeMoveException("Snake can't move in oppsite direction");
        }
        this.direction = direction;
    }

    public ArrayList<Point> getSegments() {
        return segments;
    }

    public int getLength()
    {
        return segments.size();
    }
}
