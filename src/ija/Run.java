package ija;

import ija.Block.Block;
import ija.Block.Point;
import ija.Port.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

public class Run {

    public static void run() {

        if(cycle_detection(false)) {

            Run.message("Run: Cycle detection", "Cycle detected");

            return;
        }

        // Set for every block
        for (String key: Block.Blocks.keySet()) {

            for (String key_port: Block.Blocks.get(key).connections_set.keySet()) {

                Block.Blocks.get(key).connections_set.put(key_port, false);

            }

        }

        for (String key: Point.Points.keySet()) {

            // Set for every connection block
            Point.Points.get(key).gate_block.PortIN.get(0).setValue(Point.Points.get(key).value);

            if(Point.Points.get(key).gate_block != Point.result.gate_block)
                Point.Points.get(key).gate_block.result();

        }

        Point.result.value = Point.result.port.getValue();
    }

    public static boolean cycle_detection(boolean detection) {

        if(Point.Points.isEmpty()) {
            message("No input", "No input points detected");
            return true;
        }

        if(Point.result == null) {
            message("No input", "No output points detected");
            return true;
        }

        if(Block.Blocks.isEmpty()) {
            message("No input", "No blocks detected");
            return true;
        }

        boolean cycle = false;

        for (String key : Point.Points.keySet()) {

            if(check_block(Point.Points.get(key).gate_block, new ArrayList<String>())) {
                cycle = true;
            }

        }

        if(detection) {
            if(cycle)
                message("Cycle detection", "Cycle detected!");
            else
                message("Cycle detection", "Cycle undetected");
        }

        return cycle;
    }

    private static boolean check_block(Block block, ArrayList<String> array_of_blocks) {

        if(block == null)       // Este overit TODO
            return false;

        if(array_of_blocks.contains(block.getName())) {
            return true;
        }

        array_of_blocks.add(block.getName());

        for (Port port: block.PortOUT) {

            if(port != null) {
                if(port.getConnection() != null) {
                    if(check_block(port.getConnection().getIn_port().getBlock(), array_of_blocks))
                        return true;
                }
            }

        }

        return false;
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
