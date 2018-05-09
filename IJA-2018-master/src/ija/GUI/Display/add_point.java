package ija.GUI.Display;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class add_point {

    public static String[] display() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add point");

        GridPane layout = new GridPane();

        Label label = new Label("Name: ");
        TextField name = new TextField();

        Label value = new Label("Value: ");
        TextField value_label = new TextField();

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());   //TODO

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(value, 0, 1);
        GridPane.setConstraints(value_label, 1, 1);
        GridPane.setConstraints(closeButton, 0, 2);
        GridPane.setConstraints(createButton, 1, 2);

        layout.getChildren().addAll(label, name, value, value_label, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        String ret[] = new String[2];
        ret[0] = name.getText();
        ret[1] = value_label.getText();

        return ret;

    }

}
