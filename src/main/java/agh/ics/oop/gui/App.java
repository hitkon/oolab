package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application implements IPositionChangeObserver{

    private AbstractWorldMap map;
    private GridPane panel;
    private GridPane panel2;
    private Scene scene;

    @Override
    public void init() throws Exception {
        super.init();
        panel = new GridPane();
        panel2 = new GridPane();
        TextField textField = new TextField();
        Button button = new Button();
        button.setText("Start");


        panel2.add(button, 0,0);
        panel2.add(textField, 0, 1);
        panel2.add(panel, 0, 2);
        panel2.setAlignment(Pos.CENTER);
        panel.getColumnConstraints().add(new ColumnConstraints(40));
        panel.getRowConstraints().add(new RowConstraints(40));
        panel.setAlignment(Pos.CENTER);
        panel.setGridLinesVisible(true);


        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw());
        map = new GrassField( 15);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};

        SimulationEngine engine = new SimulationEngine(map, positions, this, 1000);


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    engine.setArgs(new OptionsParser().parse(textField.getText().split(" ")));
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                Thread engineThread = new Thread(engine);
                engineThread.start();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        scene = new Scene(panel2, 800, 800);
        drawGrid2();
        primaryStage.setScene(scene);
        primaryStage.show();





    }

    public void drawGrid2(){

        panel.setGridLinesVisible(false);
        panel.getChildren().clear();
        System.out.println(map);
        Vector2d leftDown = map.getLeftDownCorner(), upRight = map.getRightUpCorner();
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
                label0.setPrefSize(60, 60);
                panel.add(label0, 0 , 0);
                int cnt = 1;
                for(int j = leftDown.x; j <= upRight.x; j++){
                    Label label = new Label(Integer.toString(j));
                    label.setAlignment(Pos.CENTER);
                    label.setPrefSize(60, 60);
                    panel.add(label, cnt++ , 0);

                }
            }
            else{
                for(int j =0; j < buf3.size(); j++){
                    Label label;
                    if(j == 0) {
                        label = new Label(buf3.get(j).split(":")[0]);
                        label.setAlignment(Pos.CENTER);
                        label.setPrefSize(60, 60);
                        panel.add(label, j, i-1);
                    }
                    else {
                        if(!buf3.get(j).equals(" ")) {
                            //System.out.println("" + (leftDown.x + j - 1 ) + ":" + (upRight.y - (i-2)) +":"+ buf3.get(j));
                            IMapElement el = ((IMapElement) map.objectAt(new Vector2d((leftDown.x + j - 1 ), (upRight.y - (i-2)))));
                            if(el != null) {
                                GuiElementBox element = null;
                                try {
                                    element = new GuiElementBox(el.getImage(), el.getLabelText());
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                panel.add(element.getVBox(), j, i - 1);
                            }
                        }
                        else{
                            label = new Label("");
                            label.setAlignment(Pos.CENTER);
                            label.setPrefSize(60, 60);
                            panel.add(label, j, i-1);
                        }
                    }


                }
            }
        }
        panel.setGridLinesVisible(true);
    }
    private void drawGrid(GridPane panel, AbstractWorldMap map ){
        System.out.println(map);
        Vector2d leftDown = map.getLeftDownCorner(), upRight = map.getRightUpCorner();
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
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(()->{drawGrid2();});
    }

//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        panel.getChildren().clear();
//        drawGrid2(panel, map);
//    }
}
