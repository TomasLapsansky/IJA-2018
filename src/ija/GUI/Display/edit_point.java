package ija.GUI.Display;

import ija.Block.Point;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class for displaying popup window at point editing
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class edit_point {

    private static boolean close;

    /**
     * Displaying function
     * @param point Reference of Point
     * @return Value from text-field
     */
    public static String display(Point point) {

        Stage window = new Stage();

        close = false;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit point " + point.getName());

        GridPane layout = new GridPane();

        Label value = new Label("Value: ");
        TextField value_label = new TextField();
        value_label.setText(String.valueOf(point.getValue()));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            close = true;
            window.close();
        });

        Button createButton = new Button("Edit");
        createButton.setOnAction(e -> window.close());

        GridPane.setConstraints(value, 0, 0);
        GridPane.setConstraints(value_label, 1, 0);
        GridPane.setConstraints(closeButton, 0, 1);
        GridPane.setConstraints(createButton, 1, 1);

        layout.getChildren().addAll(value, value_label, closeButton, createButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        if(close) {
            return null;
        } else {
            return value_label.getText();
        }
    }
}
