package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapEngineTest {
    @Test
    public void EngineTest1(){
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();
        Animal[] animals = engine.getAnimals();
        System.out.println(map);
        Assertions.assertEquals(animals[0].getPosition().toString(), "(2,0)");
        Assertions.assertEquals(animals[1].getPosition().toString(), "(3,4)");
        Assertions.assertEquals(animals[0].toString(), "S");
        Assertions.assertEquals(animals[1].toString(), "N");
    }
    @Test
    public void EngineTes2(){
        String[] args = new String[]{"f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Animal[] animals = engine.getAnimals();
        System.out.println(map);
        Assertions.assertEquals(map.canMoveTo(new Vector2d(10, 5)), false);
        Assertions.assertEquals(map.canMoveTo(new Vector2d(2, 2)), true);
        Assertions.assertEquals(map.isOccupied(new Vector2d(2, 3)), true);
        Assertions.assertEquals(map.isOccupied(new Vector2d(2, 2)), false);

        Assertions.assertEquals(animals[0].getPosition().toString(), "(2,3)");
        Assertions.assertEquals(animals[1].getPosition().toString(), "(3,4)");
        Assertions.assertEquals(animals[0].toString(), "N");
        Assertions.assertEquals(animals[1].toString(), "N");
    }
//    @Test
//    public void RectangularMapTest(){
//        IWorldMap map = new RectangularMap(5, 5);
//        Animal cat = new Animal()
//    }

}
