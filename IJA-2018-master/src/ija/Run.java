package ija;

import ija.Block.Point;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Run {

    public static void cycle_detection() {

        // TODO

    }

    public static void message(String title, String message) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        GridPane layout = new GridPane();

        Label msg = new Label(message);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        GridPane.setConstraints(msg, 0, 0);
        GridPane.setConstraints(closeButton, 0, 1);

        layout.getChildren().addAll(msg, closeButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

}
