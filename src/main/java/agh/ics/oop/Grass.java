package agh.ics.oop;

public class Grass extends AbstractMapElement{
    //private Vector2d position;

    public Grass(Vector2d pos)
    {
        super(pos);
    }
//    @Override
//    public Vector2d getPosition(){
//        return position;
//    }
    public String toString(){
        return "*";
    }

}