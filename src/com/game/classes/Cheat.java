package com.game.classes;

import com.game.classes.enumerators.Direction;
import com.game.classes.exceptions.PathNotFoundException;
import com.game.classes.exceptions.UnknownDirectionException;
import com.game.classes.interfaces.IModel;
import com.game.models.FoodManager;
import com.game.models.Level;
import com.game.models.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cheat {
    private Level level;
    private FoodManager foodManager;
    private Snake snake;
    private Point currentStartPoint;

    public Cheat(ArrayList<IModel> models){
        for (IModel model: models){
            if (model instanceof Level){
                level = (Level) model;
            }
            else if (model instanceof FoodManager){
                foodManager = (FoodManager) model;
            } else if (model instanceof Snake){
                snake = (Snake) model;
            }
        }

    }

    public Cheat(Game game){
        this(game.getContainerModels());
    }

    public ArrayList<Point> getPath() throws PathNotFoundException {
        currentStartPoint = snake.getHead();
        return getPath(level, currentStartPoint, point -> foodManager.isCollisionWith(point), point -> !snake.isBodyCollisionWith(point));
    }

    public ArrayList<Integer> getKeycodes() throws PathNotFoundException {
        return transformPathToKeycode(getPath(), level);
    }

    public static ArrayList<Point> getPath(Level level,  Point startPoint, Point endPoint) throws PathNotFoundException {
        return getPath(level, startPoint, point -> point.equals(endPoint), point -> true);
    }

    public static ArrayList<Point> getPath(Level level, Point startPoint, Predicate<Point> breakCondition, Predicate<Point> filterPoint) throws PathNotFoundException {
        LinkedList<Point> queue = new LinkedList<>();
        HashSet<Point> visited = new HashSet<>();
        visited.add(startPoint);
        HashMap<Point, Point> path = new HashMap<>();
        path.put(startPoint, null);
        queue.add(startPoint);
        Point endPoint = null;
        while (!queue.isEmpty()){
            Point point = queue.poll();
            if (breakCondition.test(point)){
                endPoint = point;
                break;
            }
            for (int x = -1 ; x <= 1; x++){
                for (int y = -1; y <= 1; y++){
                    if (x == 0 || y == 0) {
                        Point newPoint = PointAndDirectionUtils.moveCyclically(point.x + x, point.y + y, level.getWidth(), level.getHeight());
                        if (filterPoint.test(point) && !visited.contains(newPoint) && !level.isCollisionWith(newPoint) &&
                                0 <= newPoint.x && newPoint.x < level.getWidth() && 0 <= newPoint.y && newPoint.y < level.getHeight()) {
                            queue.add(newPoint);
                            visited.add(newPoint);
                            path.put(newPoint, point);
                        }
                    }
                }
            }
        }
        return getPathFromGraph(path, startPoint, endPoint);
    }

    private static ArrayList<Point> getPathFromGraph(HashMap<Point, Point> path, Point startPoint, Point endPoint) throws PathNotFoundException {
        Point point = endPoint;
        ArrayList<Point> result = new ArrayList<>();
        while (point != null){
            result.add(point);
            point = path.getOrDefault(point, null);
        }
        if (result.size() > 0 && result.get(result.size() - 1).equals(startPoint)){
            Collections.reverse(result);
            return result;
        }
        throw new PathNotFoundException();
    }

    public static ArrayList<Direction> transformPathToDirections(ArrayList<Point> path, Level level){
        ArrayList<Direction> result = new ArrayList<>();
        for (int i = 1; i < path.size(); i++){
            try {
                result.add(PointAndDirectionUtils.getDirection(path.get(i - 1), path.get(i), level.getWidth(), level.getHeight()));
            } catch (UnknownDirectionException e) {
                System.out.println("Unknown direction");
            }
        }
        return result;
    }

    public static ArrayList<Integer> transformDirectionsToKeycode(ArrayList<Direction> directions){
        return directions
                .stream()
                .map(PointAndDirectionUtils::getKeycode)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Integer> transformPathToKeycode(ArrayList<Point> path, Level level){
        return transformDirectionsToKeycode(transformPathToDirections(path, level));
    }
}
