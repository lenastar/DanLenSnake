import com.game.classes.enumerators.Direction;
import com.game.models.FoodManager;
import com.game.classes.interfaces.IController;
import com.game.models.Food;
import com.game.models.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FoodManagerTests {
    private List<IController> ExternalContainer;
    private List<Food> Foods;
    private FoodManager Manager;

    @BeforeEach
    public void init()
    {
        Foods = Arrays.asList(
                new Food(new Point(1, 2), 3),
                new Food(new Point(2, 3), 1),
                new Food(new Point(3, 3), 2),
                new Food(new Point(5, 5), 1)
        );
        Manager = new FoodManager(3);
    }

    @Test
    public void testAddFood()
    {
        Manager.addFood(Foods.get(0));
        assertEquals(Manager.count(), 1);
        assertEquals(Manager.getFood(new Point(1, 2)), Foods.get(0));
        Manager.addFood(Foods.get(1));
        assertEquals(Manager.count(), 2);
        assertEquals(Manager.getFood(new Point(2, 3)), Foods.get(1));

    }

    @Test
    public void testAddFoodOverflow()
    {
        Manager.addFood(Foods.get(0));
        Manager.addFood(Foods.get(1));
        Manager.addFood(Foods.get(2));
        Manager.addFood(Foods.get(3));
        assertEquals(Manager.count(), Manager.getLimit());
    }

    @Test
    public void testRemoveFood()
    {
        Manager.addFood(Foods.get(0));
        Manager.removeFood(Foods.get(0).getLocation());
        assertEquals(0, Manager.count());
    }

    @Test
    public void testIsFoodSuccess()
    {
        Manager.addFood(Foods.get(0));
        assertTrue(Manager.isCollisionWith(new Point(1, 2)));
    }

    @Test
    public void testIsFoodFail()
    {
        Manager.addFood(Foods.get(0));
        assertFalse(Manager.isCollisionWith(new Point(5, 2)));
    }

    @Test
    public void testMangerClear(){
        Manager.addFood(Foods.get(0));
        Manager.addFood(Foods.get(1));
        Manager.clear();
        assertEquals(Manager.count(), 0);
    }

    @Test
    public void testCollisionWithPoint(){
        Manager.addFood(Foods.get(0));
        Manager.addFood(Foods.get(1));
        assertTrue(Manager.isCollisionWith(new Point(2, 3)));
        assertFalse(Manager.isCollisionWith(new Point(4, 2)));
        Manager.clear();
    }

    @Test
    public void testCollisionWithSnake(){
        Manager.addFood(Foods.get(0));
        Snake snake = new Snake(new Point(1, 4), 3, Direction.Up);
        snake.move();
        snake.move();
        boolean result = Manager.snakeIsAliveAfterCollision(snake);
        assertTrue(result);
        assertEquals(0, Manager.count());
    }

}
