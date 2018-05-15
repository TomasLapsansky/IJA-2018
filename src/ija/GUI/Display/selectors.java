package ija.GUI.Display;

import ija.Block.Block;
import ija.Block.Point;
import ija.Port.Connection;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class for displaying popup selector alert windows
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class selectors {

    private static boolean close;

    /**
     * Selecting from connections
     * @return Reference of Connection
     */
    public static Connection connection() {

        Stage window = new Stage();

        close = false;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select connection");

        GridPane layout = new GridPane();

        ChoiceBox<String> selector = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            if(!(Connection.Connections.isEmpty())) {
                for (String key : Connection.Connections.keySet()) {
                    if(!(Connection.Connections.get(key).gate)) {
                        selector.getItems().add(key);
                    }
                }
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            close = true;
            window.close();
        });

        Button createButton = new Button("Select");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(selector, 0, 0);
        GridPane.setConstraints(closeButton, 0, 1);
        GridPane.setConstraints(createButton, 1, 1);

        layout.getChildren().addAll(selector, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        if(close) {
            return null;
        } else {
            return Connection.Connections.get(selector.getValue());
        }

    }

    /**
     * Selecting from blocks
     * @return Reference of Blocks
     */
    public static Block block() {

        Stage window = new Stage();

        close = false;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select block");

        GridPane layout = new GridPane();

        ChoiceBox<String> selector = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            for (String key : Block.Blocks.keySet()) {
                if(!(Block.Blocks.get(key).gate)){
                    selector.getItems().add(key);
                }
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            close = true;
            window.close();
        });

        Button createButton = new Button("Select");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(selector, 0, 0);
        GridPane.setConstraints(closeButton, 0, 1);
        GridPane.setConstraints(createButton, 1, 1);

        layout.getChildren().addAll(selector, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        if(close) {
            return null;
        } else {
            return Block.Blocks.get(selector.getValue());
        }
    }

    /**
     * Determinate the type of point in alert window
     * @return String with type of point
     */
    public static String setPoint() {

        Stage window = new Stage();

        close = false;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select point type");

        GridPane layout = new GridPane();

        ChoiceBox<String> selector = new ChoiceBox<>();

        selector.getItems().add("Start");
        selector.getItems().add("End");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            close = true;
            window.close();
        });

        Button createButton = new Button("Select");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(selector, 0, 0);
        GridPane.setConstraints(closeButton, 0, 1);
        GridPane.setConstraints(createButton, 1, 1);

        layout.getChildren().addAll(selector, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        if(close) {
            return null;
        } else {
            return selector.getValue();
        }
    }

    /**
     * Selecting from points
     * @param endPoint Determinate if display End point or not
     * @return Reference of Point
     */
    public static Point point(boolean endPoint) {

        Stage window = new Stage();

        close = false;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select point");

        GridPane layout = new GridPane();

        ChoiceBox<String> selector = new ChoiceBox<>();

        if (!(Point.Points.isEmpty())) {
            for (String key : Point.Points.keySet()) {
                selector.getItems().add(key);
            }
        }

        if(Point.result != null) {
            if (!endPoint) {
                selector.getItems().remove(Point.result.getName());
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            close = true;
            window.close();
        });

        Button createButton = new Button("Select");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(selector, 0, 0);
        GridPane.setConstraints(closeButton, 0, 1);
        GridPane.setConstraints(createButton, 1, 1);

        layout.getChildren().addAll(selector, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        if(close){
            return null;
        } else {
            return Point.Points.get(selector.getValue());
        }
    }

}
