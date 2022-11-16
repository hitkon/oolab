package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap{

    private List<IMapElement> Objects;
    private int grassSpawnLength;

    private Vector2d getRandomFreePlace(){
        Random rand = new Random();
        Vector2d res;
        do{
            res = new Vector2d(rand.nextInt(grassSpawnLength), rand.nextInt(grassSpawnLength));
        }
        while (this.isOccupied(res));
        return res;
    }

    public GrassField(int n){
        Objects = new ArrayList<>();
        grassSpawnLength = (int)(Math.sqrt(n * 10));
        Objects.add(new Grass(new Vector2d(3, 5)));
        for (int i = 0; i < n-1; i++){
            Vector2d newGrassPosition = getRandomFreePlace();
            Objects.add(new Grass(newGrassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for(IMapElement Obj : Objects){
            if(Obj.getPosition().equals(position) && Obj instanceof Animal)
                return false;
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            if(isOccupied((animal.getPosition()))){
                Objects.remove(objectAt(animal.getPosition()));
                Objects.add(new Grass(getRandomFreePlace()));
                //objectAt(animal.getPosition())
            }
            Objects.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position)!= null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(IMapElement Obj : Objects)
            if(Obj.getPosition().equals(position))
                return Obj;
        return null;
    }

    @Override
    public void remove(Animal animal) {
        Objects.remove(animal);
    }

    public Vector2d getLeftDownCorner(){
        Vector2d leftDown = new Vector2d(0, 0);
        for(IMapElement Obj : Objects){
            leftDown = Obj.getPosition().lowerLeft(leftDown);
        }
        return leftDown;
    }

    public Vector2d getRightUpCorner(){
        Vector2d rightUp = new Vector2d(grassSpawnLength-1, grassSpawnLength-1);
        for(IMapElement Obj : Objects){
            rightUp = Obj.getPosition().upperRight(rightUp);
        }
        return  rightUp;
    }
}
