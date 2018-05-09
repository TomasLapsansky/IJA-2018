package ija.GUI.Display;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class add_block {

    public static String display() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add block");

        GridPane layout = new GridPane();

        Label label = new Label("Name: ");
        TextField name = new TextField();

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());   //TODO

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(closeButton, 0, 1);
        GridPane.setConstraints(createButton, 1, 1);

        layout.getChildren().addAll(label, name, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return name.getText();

    }

}
