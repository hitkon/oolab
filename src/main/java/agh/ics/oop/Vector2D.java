package agh.ics.oop;

public class Vector2D {
    public final int x;
    public final int y;
    Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    boolean precedes(Vector2D vec){
        if(x <= vec.x && y <= vec.y)
            return true;
        return false;
    }

    boolean follows(Vector2D vec){
        if(x >= vec.x && y >= vec.y)
            return true;
        return false;
    }

    Vector2D add(Vector2D vec){
        return new Vector2D(this.x + vec.x, this.y + vec.y);
    }

    Vector2D subtract(Vector2D vec){
        return new Vector2D(this.x - vec.x, this.y - vec.y);
    }

    Vector2D upperRight(Vector2D vec){
        return new Vector2D(Math.max(x, vec.x), Math.max(y, vec.y));
    }

    Vector2D lowerLeft(Vector2D vec){
        return new Vector2D(Math.min(x, vec.x), Math.min(y, vec.y));
    }

    Vector2D opposite(){
        return new Vector2D(-x, -y);
    }

    public boolean equals(Object other){
        if(this == other)
            return true;
        if(!(other instanceof Vector2D))
            return false;
        Vector2D vec = (Vector2D) other;

        if(x == vec.x && y == vec.y)
            return true;
        return false;
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }
}
