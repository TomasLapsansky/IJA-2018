package ija;

import ija.Block.Block;
import ija.Block.Point;
import ija.Port.OUT_Port;
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

        if(cycle_detection()) {
            return;
        }

        LinkedList<Block> toCheck = new LinkedList<>();

        for (String key : Point.Points.keySet()) {
            if(Point.Points.get(key) != Point.result) {
                Point.Points.get(key).gate_block.getInput(key).setValue(Point.Points.get(key).value);
                toCheck.push(Point.Points.get(key).gate_block);
            }
        }

        while(toCheck.size() != 0) {

            Block block = toCheck.removeLast();    // Mozna optimalizacia

            block.result();     // Result and tranfer to other blocks

            for (OUT_Port act_output: block.PortOUT) {

                if(act_output.getConnection() != null) {

                    Block next_block = act_output.getConnection().getIn_port().getBlock();

                    toCheck.push(next_block);
                }
            }
        }

        //HERE
    }

    public static boolean cycle_detection() {

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

        // Queue to check
        LinkedList<Block> toCheck = new LinkedList<>();
        Map<String, Block> checked = new HashMap<>();

        for (String key : Point.Points.keySet()) {
            if(Point.Points.get(key) != Point.result) {
                toCheck.push(Point.Points.get(key).gate_block);
            }
        }

        while(toCheck.size() != 0) {

            Block block = toCheck.removeLast();    // Mozna optimalizacia

            if(checked.get(block.getName()) != null) {
                message("Cycle detection", "Cycle detected!");
                return true;
            }

            for (OUT_Port act_output: block.PortOUT) {

                if(act_output.getConnection() != null) {

                    Block next_block = act_output.getConnection().getIn_port().getBlock();

                    toCheck.push(next_block);
                }
            }

            checked.put(block.getName(), block);
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
