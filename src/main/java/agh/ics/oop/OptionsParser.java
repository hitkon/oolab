package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        int size = 0;
        for(String arg : args)
            if(arg.equals("l") || arg.equals("r") || arg.equals("f") || arg.equals("b"))
                size++;
        MoveDirection[] ans = new MoveDirection[size];
        for(int i = 0; i < args.length; i++){
            switch (args[i]){
                case "f" -> ans[i] = MoveDirection.FORWARD;
                case "b" -> ans[i] = MoveDirection.BACKWARD;
                case "l" -> ans[i] = MoveDirection.LEFT;
                case "r" -> ans[i] = MoveDirection.RIGHT;
            }
        }
        return ans;
    }
}
