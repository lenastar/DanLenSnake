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
    public void testBadSizeForMap(){
        assertThrows(LevelBadSizeException.class, () -> {
            MapGUI map = new MapGUI(2, 2, 30);
        });
    }

    @Test
    public void testBadPointForDrawing() throws LevelBadSizeException {
        //TODO: remake test. Need in test to check specific methods of IView anon class
        MapGUI map = new MapGUI(5, 5, 10);
        JDialog dlg = new JDialog((JFrame) null, "Snake");
        IView<Context> view = new IView<Context>() {
            @Override
            public void paint(Context context) throws IndexOutOfBoundsException{
                assertThrows(IndexOutOfBoundsException.class, () -> {
                    context.map.drawPoint(context.g, new Point(200, 200), Color.DARK_GRAY);
                });
                assertThrows(IndexOutOfBoundsException.class, () -> {
                    context.map.drawImagePoint(context.g, new Point(200, 200), Images.getBanana());
                });
            }
        };
        map.addView(view);
        dlg.getContentPane().add(map);
        dlg.setVisible(true);
        dlg.pack();
    }
}
