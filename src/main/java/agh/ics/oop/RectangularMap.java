package agh.ics.oop;

import java.util.List;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {

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
        if(!isOccupied(animal.getPosition())){
            map[animal.getPosition().x][animal.getPosition().y] = animal;
            return true;
        }
        return false;
    }
    @Override
    public void remove(Animal animal){
        map[animal.getPosition().x][animal.getPosition().y] = null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return map[position.x][position.y] != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return map[position.x][position.y];
    }

    public Vector2d getLeftDownCorner(){
        return new Vector2d(0,0);
    }

    public Vector2d getRightUpCorner(){
        return new Vector2d(width-1, height-1);
    }
}
