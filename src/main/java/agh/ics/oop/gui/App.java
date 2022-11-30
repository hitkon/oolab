package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw());
        AbstractWorldMap map = new GrassField( 15);
//        AbstractWorldMap map = new RectangularMap( 10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d leftDown = map.getLeftDownCorner(), upRight = map.getRightUpCorner();
        GridPane panel = new GridPane();
        panel.getColumnConstraints().add(new ColumnConstraints(40));
        panel.getRowConstraints().add(new RowConstraints(40));

        System.out.println(map);
        String[] buf = map.toString().split("\n");
        int panelHeight = buf.length, panelWidth = buf[0].split(" ").length;
        //String[][] stringMap = new String[panelWidth][panelHeight];
        for (int i = 0; i < panelHeight; i++){
            List<String> buf3 = new ArrayList<>();
            String[] buf2;
            if(i == 0){
                buf2 = buf[i].split(" ");
            } else if (i == 1 || i == panelHeight-1) {
                continue;
            }
            else{
                buf2 = buf[i].split("\\|");
            }

            for (String s : buf2){
                if(!s.equals("") && !s.equals("|") && !s.equals(":") && !s.equals("\r"))
                    buf3.add(s);
            }

            if(i == 0){
                Label label0 = new Label("y/x");
                label0.setAlignment(Pos.CENTER);
                label0.setPrefSize(40, 40);
                panel.add(label0, 0 , 0);
                int cnt = 1;
                for(int j = leftDown.x; j <= upRight.x; j++){
                    Label label = new Label(Integer.toString(j));
                    label.setAlignment(Pos.CENTER);
                    label.setPrefSize(40, 40);
                    panel.add(label, cnt++ , 0);

                }
            }
            else{
                for(int j =0; j < buf3.size(); j++){
                    Label label;
                    if(j == 0)
                        label = new Label(buf3.get(j).split(":")[0]);
                    else
                        label = new Label(buf3.get(j));
                    label.setAlignment(Pos.CENTER);
                    label.setPrefSize(40, 40);
                    panel.add(label, j, i-1);
                }
            }
        }

        panel.setAlignment(Pos.CENTER);
        panel.setGridLinesVisible(true);
        Scene scene = new Scene(panel, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
