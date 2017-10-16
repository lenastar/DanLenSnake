package com.game.classes.enumerators;

import java.awt.*;

public enum Direction {
    Up(new Point(0, -1)){
        @Override
        public Direction opposite() {
            return Down;
        }
    },
    Down(new Point(0, 1)) {
        @Override
        public Direction opposite() {
            return Up;
        }
    },
    Right(new Point(1, 0)) {
        @Override
        public Direction opposite() {
            return Left;
        }
    },
    Left(new Point(-1, 0)) {
        @Override
        public Direction opposite() {
            return Right;
        }
    };

    private Point point;
    Direction(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public abstract Direction opposite();
}
