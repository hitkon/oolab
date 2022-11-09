package agh.ics.oop;

import java.util.List;

public class RectangularMap implements IWorldMap{

    private int width, height;
    private Object[][] map;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        map = new Object[width][height];
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width-1,height-1)) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(!isOccupied(animal.getAnimalPos())){
            map[animal.getAnimalPos().x][animal.getAnimalPos().y] = animal;
            return true;
        }
        return false;
    }
    @Override
    public void remove(Animal animal){
        map[animal.getAnimalPos().x][animal.getAnimalPos().y] = null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return map[position.x][position.y] != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map[position.x][position.y];
    }
}
