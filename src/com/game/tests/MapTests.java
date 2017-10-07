package com.game.tests;

import org.junit.jupiter.api.Test;
import com.game.models.MapConsole;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapTests{
    @Test
    public void testFillMapSuccess(){
        MapConsole mapConsole = new MapConsole(4,4);
        char[][] expectedResult = {
            {'+','-','-','+'},
            {'|',' ',' ','|'},
            {'|',' ',' ','|'},
            {'+','-','-','+'}};
        assertArrayEquals(expectedResult, mapConsole.getCanvas());
    }

    @Test
    public void testSetItem(){
        MapConsole mapConsole = new MapConsole(4,5);
        char item1 = '$';
        char item2 = '~';
        mapConsole.setItem(new Point(1 , 2),item1);
        mapConsole.setItem(new Point(2, 1),item2);
        assertEquals(item1, mapConsole.getItem(1,2));
        assertEquals(item2, mapConsole.getItem(2,1));
    }

    @Test
    public void testSetItemFail() throws ArrayIndexOutOfBoundsException{
        MapConsole mapConsole = new MapConsole(2,2);
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> {
            mapConsole.setItem(new Point(3, 3),'a');});
    }
}

