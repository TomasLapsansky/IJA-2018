package ija.GUI.Display;

import ija.Block.Block;
import ija.Port.Connection;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class selectors {

    public static Connection connection() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select connection");

        GridPane layout = new GridPane();

        ChoiceBox<String> selector = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            for (String key : Connection.Connections.keySet()) {
                selector.getItems().add(key);
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());   //TODO

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

        return Connection.Connections.get(selector.getValue());

    }

    public static Block block() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select block");

        GridPane layout = new GridPane();

        ChoiceBox<String> selector = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            for (String key : Block.Blocks.keySet()) {
                selector.getItems().add(key);
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());   //TODO

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

        return Block.Blocks.get(selector.getValue());

    }

    public static String setPoint() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Select point type");

        GridPane layout = new GridPane();

        ChoiceBox<String> selector = new ChoiceBox<>();

        selector.getItems().add("Start");
        selector.getItems().add("End");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());   //TODO

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

        return selector.getValue();

    }

}
