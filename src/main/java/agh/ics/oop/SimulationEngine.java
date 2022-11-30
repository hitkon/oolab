package agh.ics.oop;

import java.util.Random;

public class SimulationEngine implements IEngine{

    private MoveDirection[] args;
    private AbstractWorldMap map;
    private Vector2d[] positions;
    private Animal[] animals;

    public SimulationEngine(MoveDirection[] args, AbstractWorldMap map, Vector2d[] positions){
        this.args = args;
        this.map = map;
        this.positions = positions;
    }

    @Override
    public Animal[] getAnimals(){return animals;};

    @Override
    public void run() {

        int numberOfAnimals = positions.length;
        animals = new Animal[numberOfAnimals];
        for (int i = 0; i < numberOfAnimals; i++) {
            animals[i] = new Animal(map, positions[i]);
            animals[i].addObserver(map);
            animals[i].addObserver(map.getBounds());
        }

        if (map instanceof GrassField)
            map.getBounds().forceUpdate();


        //System.out.println(map);
        for (int i = 0; i < args.length; i++){
            animals[i%numberOfAnimals].move(args[i]);
        }
        for (int i = 0; i < numberOfAnimals; i++) {
            animals[i].removeObserver(map);
            animals[i].removeObserver(map.getBounds());
        }
    }
}
