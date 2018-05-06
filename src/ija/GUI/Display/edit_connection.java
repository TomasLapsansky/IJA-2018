package ija.GUI.Display;

import java.util.*;
import ija.Block.Block;
import ija.Port.Connection;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class edit_connection {

    public static String[] display(Connection connection) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit connection " + connection.getName());

        GridPane layout = new GridPane();

        Label label_input = new Label("Input: ");
        ChoiceBox<String> input = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            for (String key : Block.Blocks.keySet()) {
                input.getItems().add(key);
            }
        }

        input.setValue(connection.getIn_port().getBlock().getName());

        Label label_output = new Label("Output: ");
        ChoiceBox<String> output = new ChoiceBox<>();

        if (!(Block.Blocks.isEmpty())) {
            for (String key : Block.Blocks.keySet()) {
                output.getItems().add(key);
            }
        }

        output.setValue(connection.getOut_port().getBlock().getName());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());   //TODO

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(label_input, 0, 0);
        GridPane.setConstraints(input, 1, 0);
        GridPane.setConstraints(label_output, 0, 1);
        GridPane.setConstraints(output, 1, 1);
        GridPane.setConstraints(closeButton, 0, 2);
        GridPane.setConstraints(createButton, 1, 2);

        layout.getChildren().addAll(label_input, input, label_output, output, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        String ret[] = new String[3];
        ret[0] = connection.getName();
        ret[1] = input.getValue();
        ret[2] = output.getValue();

        return ret;
    }

}
