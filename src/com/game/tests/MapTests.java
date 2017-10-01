package com.game.tests;

import org.junit.jupiter.api.Test;
import com.game.models.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapTests{
    @Test
    public void testFillMapSuccess(){
        Map map = new Map(4,4);
        char[][] expectedResult = {
            {'+','-','-','+'},
            {'|',' ',' ','|'},
            {'|',' ',' ','|'},
            {'+','-','-','+'}};
        assertArrayEquals(expectedResult,map.getCanvas());
    }

    @Test
    public void testFail(){
        Map map = new Map(3,3);
        char[][] failResult = {{'-','+'},{'|'}};
        assertNotEquals(failResult.length,map.getHeight());
    }

    @Test
    public void testSetItem(){
        Map map = new Map(4,5);
        char item1 = '$';
        char item2 = '~';
        map.setItem(1,2,item1);
        map.setItem(2,1,item2);
        assertEquals(item1,map.getItem(1,2));
        assertEquals(item2,map.getItem(2,1));
    }

    @Test
    public void testSetItemFail() throws ArrayIndexOutOfBoundsException{
        Map map = new Map(2,2);
        assertThrows(ArrayIndexOutOfBoundsException.class,() -> {map.setItem(3,3,'a');});
    }
}

