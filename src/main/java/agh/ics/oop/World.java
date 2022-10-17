package agh.ics.oop;

public class World {
//    public static void run(String[] args){
//        System.out.println("Idzie do przodu");
//        System.out.print(args[0]);
//        if(args.length != 1) {
//            for (int i = 1; i < args.length; i++) {
//                System.out.print("," + args[i]);
//            }
//        }
//        System.out.println();
//    }

//    public static void run(String[] args){
//        for (String arg : args){
//            switch(arg){
//                case "f":
//                    System.out.println("Zwierzak idzie do przodu");
//                    break;
//                case "b":
//                    System.out.println("Zwierzak idzie do tyłu");
//                    break;
//                case "l":
//                    System.out.println("Zwierzak skręca w lewo");
//                    break;
//                case "r":
//                    System.out.println("Zwierzak skręca w prawo");
//                    break;
//                default:
//                    System.out.println("Zwierzak skręca w prawo");
//            }
//        }
//    }

    public static void run(Direction[] directions){
        for (int i = 0;  i< directions.length; i++){
            if(directions[i] == null)
                continue;
            switch(directions[i]){
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
            }
        }
    }

    public static Direction[] processInput(String[] args){

        Direction[] directions = new Direction[args.length];
        for(int i = 0; i < args.length; i++){
            switch(args[i]){
                case "f":
                    directions[i] = Direction.FORWARD;
                    break;
                case "b":
                    directions[i] = Direction.BACKWARD;
                    break;
                case "l":
                    directions[i] = Direction.LEFT;
                    break;
                case "r":
                    directions[i] = Direction.RIGHT;
                    break;
            }
        }
        return directions;
    }

    public static void main(String[] args){
//        System.out.println("Start");
//        run(processInput(args));
//        System.out.println("Stop");
        Vector2D pos1 = new Vector2D(1, 2);
        System.out.println(pos1);
        Vector2D pos2 = new Vector2D(-2, 1);
        System.out.println(pos2);
        System.out.println(pos1.add(pos2));


    }
}
