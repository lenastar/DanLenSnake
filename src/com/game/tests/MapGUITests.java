import com.game.classes.Context;
import com.game.classes.Images;
import com.game.classes.MapGUI;
import com.game.classes.exceptions.LevelBadSizeException;
import com.game.classes.interfaces.IModel;
import com.game.classes.interfaces.IView;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapGUITests {
    @Test
    public void testBadPointForDrawing() {
        MapGUI map = new MapGUI(20, 30, 30);
    //    assertThrows(IndexOutOfBoundsException.class, () -> { map.drawImagePoint(map.getGraphics(), new Point(20000, 20000), Images.getBanana());});
     //   assertThrows(IndexOutOfBoundsException.class, () -> { map.drawPoint(map.getGraphics(), new Point(20000, 20000), Color.DARK_GRAY);});
    };


}
