import com.game.classes.Game;
import com.game.classes.MapGUI;
import com.game.classes.Model;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.models.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class GameTests {
    private Game game;
    private MapGUI map;

    @BeforeEach
    public void init() throws LevelBadSizeException {
        MapGUI map = new MapGUI(4, 4, 10);
        Game game = new Game(map);
    }

    @Test
    public void testAddInstance() throws NoSuchMethodException {
        game.addInstance(Model.createSnake(new Point(1, 2), 2));
        assertEquals(1, game.getContainerModels().size());
        assertEquals(1, map.getViews().size());
        assertEquals(1, game.getContainerControllers().size());
    }

}
