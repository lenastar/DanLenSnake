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
        MapGUI map = new MapGUI(5, 5, 10);
        JDialog dlg = new JDialog((JFrame) null, "Snake");
        IView<IModel, Context> view = new IView<IModel, Context>() {
            @Override
            public IModel getModel() {
                return null;
            }

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
