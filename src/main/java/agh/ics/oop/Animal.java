package agh.ics.oop;

import java.awt.image.IndexColorModel;

public class Animal {
    private static Vector2d mapRightUpCorner = new Vector2d(4, 4);
    private static Vector2d mapLeftDownCorner = new Vector2d(0, 0);
    private IWorldMap map;
    private MapDirection animalDir;
    private Vector2d animalPos;
    private Animal(){
        animalDir = MapDirection.NORTH;
        animalPos = new Vector2d(2,2);
    }

    public Animal(IWorldMap map){
        animalDir = MapDirection.NORTH;
        animalPos = new Vector2d(2,2);
        this.map = map;
        map.place(this);
    }

    public Animal(IWorldMap map, Vector2d initialPos){
        animalDir = MapDirection.NORTH;
        animalPos = initialPos;
        this.map = map;
        map.place(this);
    }

    public boolean isAt(Vector2d position){
        return animalPos.equals(position);
    }

    private void correctPositiob(){
        animalPos = mapRightUpCorner.lowerLeft(animalPos);
        animalPos = mapLeftDownCorner.upperRight(animalPos);
    }

    public Vector2d getAnimalPos() {
        return animalPos;
    }

    public void move(MoveDirection dir){
        switch(dir){
            case LEFT -> animalDir = animalDir.previous();
            case RIGHT -> animalDir = animalDir.next();
            case FORWARD -> {
                map.remove(this);
                animalPos = map.canMoveTo(animalPos.add(animalDir.toUnitVector())) ? animalPos.add(animalDir.toUnitVector()) : animalPos;
                map.place(this);
            }
            case BACKWARD -> {
                map.remove(this);
                animalPos = map.canMoveTo(animalPos.add(animalDir.toUnitVector().opposite())) ? animalPos.add(animalDir.toUnitVector().opposite()) : animalPos;
                map.place(this);
            }
        }
    }

    public String toString(){
        return animalDir.toString();
    }

}
