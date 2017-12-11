package com.game.models;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.SnakeOppositeMoveException;
import com.game.classes.interfaces.IModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake implements IModel{
    private ArrayList<Point> segments;
    private Direction direction;
    private int length;

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
        this.length = segments.size();
    }

    public Snake(Point head, int length)
    {
        this(head, length, Direction.Left);
    }

    public void move()
    {
        Point head = new Point(getHead());
        head.translate(direction.getPoint().x, direction.getPoint().y);
        segments.add(head);
        if (length < segments.size()) {
            segments.remove(0);
        }
    }

    public Point getHead()
    {
        return segments.get(segments.size() - 1);
    }

    public void grow(int length)
    {
        this.length += length;
    }

    public boolean isCollisionWith(Point point)
    {
        return isBodyCollisionWith(point) || isHeadCollisionWith(point);
    }

    @Override
    public boolean snakeIsAliveAfterCollision(Snake snake) {
        if (snake == this)
            return !(isBodyCollisionWith(getHead()));
        else return !(snake.isCollisionWith(getHead())) ;

    }

    public boolean isBodyCollisionWith(Point point)
    {
        return segments
                .stream()
                .limit(segments.size()-1)
                .anyMatch(p->p.equals(point));
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
            throw new SnakeOppositeMoveException();
        }
        this.direction = direction;
    }

    public ArrayList<Point> getSegments() {
        return segments;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
