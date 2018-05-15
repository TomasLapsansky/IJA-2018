package ija.GUI.Display;

import ija.Block.Block;
import ija.Block.Point;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class add_connection {

    private static boolean close;

    public static String[] display() {

        Stage window = new Stage();

        close = false;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add connection");

        GridPane layout = new GridPane();

        Label label_name = new Label("Name: ");
        TextField name = new TextField();

        Label label_input = new Label("Input: ");
        ChoiceBox<String> input = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            for (String key : Block.Blocks.keySet()) {
                if(Point.result != null) {
                    if (!(Point.Points.get(key) != null && !(key.equals(Point.result.getName()))))
                        input.getItems().add(key);
                } else {
                    if (Point.Points.get(key) == null)
                        input.getItems().add(key);
                }
            }
        }

        Label label_output = new Label("Output: ");
        ChoiceBox<String> output = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            for (String key : Block.Blocks.keySet()) {
                if(Point.result != null) {
                    if (!(Point.result.getName().equals(key)))
                        output.getItems().add(key);
                } else {
                    output.getItems().add(key);
                }
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            close = true;
            window.close();
        });

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(label_name, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(label_input, 0, 1);
        GridPane.setConstraints(input, 1, 1);
        GridPane.setConstraints(label_output, 0, 2);
        GridPane.setConstraints(output, 1, 2);
        GridPane.setConstraints(closeButton, 0, 3);
        GridPane.setConstraints(createButton, 1, 3);

        layout.getChildren().addAll(label_name, name, label_input, input, label_output, output, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        String ret[] = new String[3];

        if(close) {

            ret[0] = null;
            ret[1] = null;
            ret[2] = null;

        } else {
            ret[0] = name.getText();
            ret[1] = input.getValue();
            ret[2] = output.getValue();
        }

        return ret;
    }

}
