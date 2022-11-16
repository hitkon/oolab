package agh.ics.oop;

import java.awt.image.IndexColorModel;

public class Animal extends AbstractMapElement{
    private static Vector2d mapRightUpCorner = new Vector2d(4, 4);
    private static Vector2d mapLeftDownCorner = new Vector2d(0, 0);
    private IWorldMap map;
    private MapDirection animalDir;
    //private Vector2d animalPos;
    private Animal(){
        super(new Vector2d(2,2));
        animalDir = MapDirection.NORTH;
        //animalPos = new Vector2d(2,2);
    }

    public Animal(IWorldMap map){
        super(new Vector2d(2,2));
        animalDir = MapDirection.NORTH;
        //animalPos = new Vector2d(2,2);
        this.map = map;
        map.place(this);
    }

    public Animal(IWorldMap map, Vector2d initialPos){
        super(initialPos);
        animalDir = MapDirection.NORTH;
        //animalPos = initialPos;
        this.map = map;
        map.place(this);
    }

    public boolean isAt(Vector2d position){
        return getPosition().equals(position);
    }

//    @Override
//    public Vector2d getPosition(){return animalPos;}

    public void move(MoveDirection dir){
        switch(dir){
            case LEFT -> animalDir = animalDir.previous();
            case RIGHT -> animalDir = animalDir.next();
            case FORWARD -> {
                map.remove(this);
                setPosition(map.canMoveTo(getPosition().add(animalDir.toUnitVector())) ? getPosition().add(animalDir.toUnitVector()) : getPosition());
                map.place(this);
            }
            case BACKWARD -> {
                map.remove(this);
                setPosition(map.canMoveTo(getPosition().add(animalDir.toUnitVector().opposite())) ? getPosition().add(animalDir.toUnitVector().opposite()) : getPosition());
                map.place(this);
            }
        }
    }

    public String toString(){
        return animalDir.toString();
    }

}
