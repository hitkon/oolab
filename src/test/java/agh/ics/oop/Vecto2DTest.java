package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vecto2DTest {
    @Test
    public void equalTest(){
        Vector2D vec1 = new Vector2D(1, 2);
        Assertions.assertEquals(vec1.equals(vec1), true);
        Vector2D vec2 = new Vector2D(3, 2);
        Assertions.assertEquals(vec1.equals(vec2), false);
        Vector2D vec3 = new Vector2D(3, 2);
        Assertions.assertEquals(vec3.equals(vec2), true);
    }

    @Test
    public void toStringTest(){
        Vector2D vec = new Vector2D(1, 2);
        Assertions.assertEquals(vec.toString(), "(1,2)");
    }

    @Test
    public void precedesTest(){
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(1, 3);
        Assertions.assertEquals(vec1.precedes(vec2), true);
        Assertions.assertEquals(vec2.precedes(vec1), false);
        Assertions.assertEquals(vec1.precedes(vec1), true);
    }

    @Test
    public void followsTest(){
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(1, 3);
        Assertions.assertEquals(vec1.follows(vec2), false);
        Assertions.assertEquals(vec2.follows(vec1), true);
        Assertions.assertEquals(vec1.follows(vec1), true);
    }

    @Test
    public void upperRightTest(){
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(0, 3);
        Assertions.assertEquals(vec1.upperRight(vec2), new Vector2D(1, 3));
    }

    @Test
    public void lowerLeftTest(){
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(0, 3);
        Assertions.assertEquals(vec1.lowerLeft(vec2), new Vector2D(0, 2));
    }

    @Test
    public void addTest(){
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(0, 3);
        Assertions.assertEquals(vec1.add(vec2), new Vector2D(1, 5));
    }

    @Test
    public void subtractTest(){
        Vector2D vec1 = new Vector2D(1, 2);
        Vector2D vec2 = new Vector2D(0, 3);
        Assertions.assertEquals(vec1.subtract(vec2), new Vector2D(1, -1));
    }

    @Test
    public void oppositeTest(){
        Vector2D vec = new Vector2D(1, 2);
        Assertions.assertEquals(vec.opposite(), new Vector2D(-1, -2));
    }
}
