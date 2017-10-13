import com.game.models.FoodManager;
import com.game.classes.interfaces.IController;
import com.game.models.Food;
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
        assertTrue(Manager.getLocations().contains(Foods.get(0).getLocation()));
        assertTrue(Manager.getLocations().contains(Foods.get(1).getLocation()));
        assertTrue(Manager.getLocations().contains(Foods.get(2).getLocation()));
    }

    @Test
    public void testRemoveFood()
    {
        Manager.addFood(Foods.get(0));
        assertEquals(1, Manager.count());
        Manager.removeFood(Foods.get(0).getLocation());
        assertEquals(0, Manager.count());
    }

    @Test
    public void testIsFoodSuccess()
    {
        Manager.addFood(Foods.get(0));
        assertTrue(Manager.isFood(new Point(1, 2)));
    }

    @Test
    public void testIsFoodFail()
    {
        Manager.addFood(Foods.get(0));
        assertFalse(Manager.isFood(new Point(5, 2)));
    }

    @Test
    public void testMangerClear(){
        Manager.addFood(Foods.get(0));
        Manager.addFood(Foods.get(1));
        assertEquals(Manager.count(), 2);
        Manager.clear();
        assertEquals(Manager.count(), 0);
    }

}
