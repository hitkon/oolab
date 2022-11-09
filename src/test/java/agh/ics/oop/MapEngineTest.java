package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapEngineTest {
    @Test
    public void EngineTest1(){
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Animal[] animals = engine.getAnimals();
        Assertions.assertEquals(animals[0].getAnimalPos().toString(), "(2,0)");
        Assertions.assertEquals(animals[1].getAnimalPos().toString(), "(3,4)");
        Assertions.assertEquals(animals[0].toString(), "S");
        Assertions.assertEquals(animals[1].toString(), "N");
    }
    @Test
    public void EngineTes2(){
        String[] args = new String[]{"f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Animal[] animals = engine.getAnimals();
        Assertions.assertEquals(animals[0].getAnimalPos().toString(), "(2,3)");
        Assertions.assertEquals(animals[1].getAnimalPos().toString(), "(3,4)");
        Assertions.assertEquals(animals[0].toString(), "N");
        Assertions.assertEquals(animals[1].toString(), "N");
    }
}
