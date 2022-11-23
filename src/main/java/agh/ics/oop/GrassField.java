package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap{

    //private List<IMapElement> Objects;
    private Map<Vector2d, IMapElement> Objects;
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
        //Objects = new ArrayList<>();
        Objects = new HashMap<>();
        grassSpawnLength = (int)(Math.sqrt(n * 10));
        Objects.put(new Vector2d(3,5 ), new Grass(new Vector2d(3, 5)));
        for (int i = 0; i < n-1; i++){
            Vector2d newGrassPosition = getRandomFreePlace();
            Objects.put(newGrassPosition, new Grass(newGrassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object buf =  objectAt(position);
        return buf == null || (buf != null && buf instanceof Grass);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            if(isOccupied((animal.getPosition()))){
                Objects.remove(objectAt(animal.getPosition()));
                Vector2d newGrassPosition = getRandomFreePlace();
                Objects.put(newGrassPosition, new Grass(newGrassPosition));
            }
            Objects.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        //Object obj = objectAt(position);
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return Objects.get(position);
    }

    @Override
    public void remove(Animal animal) {
        Objects.remove(animal.getPosition());
    }

    public Vector2d getLeftDownCorner(){
        Vector2d leftDown = new Vector2d(0, 0);
        for(IMapElement Obj : Objects.values()){
            leftDown = Obj.getPosition().lowerLeft(leftDown);
        }
        return leftDown;
    }

    public Vector2d getRightUpCorner(){
        Vector2d rightUp = new Vector2d(grassSpawnLength-1, grassSpawnLength-1);
        for(IMapElement Obj : Objects.values()){
            rightUp = Obj.getPosition().upperRight(rightUp);
        }
        return  rightUp;
    }
}
