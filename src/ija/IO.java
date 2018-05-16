package ija;

import ija.Block.*;
import ija.GUI.Display.canvas;
import ija.GUI.main_gui;
import ija.Port.Connection;
import ija.Port.IN_Port;
import ija.Port.OUT_Port;
import javafx.scene.control.TreeItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.UUID;

/**
 * Handles saving and loading of created schemas
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class IO {

    /**
     * Saves raw project structure to file chosen by FileChooser
     */
    public static void save() {

        Stage stage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                PrintWriter writer = new PrintWriter(file);

                // Saving content

                // Header
                writer.println("*BlockSchema2018Save*");

                // Points

                for (String key: Point.Points.keySet()) {

                    writer.println("*Point*");

                    Point point = Point.Points.get(key);

                    if(point instanceof Start_Point) {
                        writer.println("Start");
                    } else if(point instanceof End_Point) {
                        writer.println("End");
                    }

                    writer.println(point.getName());
                    writer.println(point.getValue());
                    writer.println(point.canvas.getLayoutX());
                    writer.println(point.canvas.getLayoutY());

                }

                // Blocks

                for (String key: Block.Blocks.keySet()) {

                    Block block = Block.Blocks.get(key);

                    if(!block.gate) {

                        writer.println("*Block*");

                        if (block instanceof ADD_Block) {
                            writer.println("ADD");
                        } else if (block instanceof SUB_Block) {
                            writer.println("SUB");
                        } else if (block instanceof MUL_Block) {
                            writer.println("MUL");
                        } else if (block instanceof DIV_Block) {
                            writer.println("DIV");
                        }

                        writer.println(block.getName());
                        writer.println(block.canvas.getLayoutX());
                        writer.println(block.canvas.getLayoutY());
                    }
                }

                // Connections

                for (Connection connection: Connection.sequence) {

                    //Connection connection = Connection.Connections.get(key);

                    if(!connection.gate) {

                        writer.println("*Connection*");

                        writer.println(connection.getName());
                        writer.println(connection.getOut_port().getBlock().getName());
                        writer.println(connection.getIn_port().getBlock().getName());

                    }

                }

                writer.println("*END*");

                writer.close();

            } catch (IOException ex) {
                Run.message("Warning", "File to save not found");
            }

            main_gui.stage.setTitle(file.getName());
        }

    }

    /**
     * Loads raw project structure from file chosen by FileChooser and creates it
     */
    public static void load() {

        Stage stage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        File file = fileChooser.showOpenDialog(stage);

        if(file != null) {

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                line = br.readLine();

                if(line.compareTo("*BlockSchema2018Save*") != 0) {
                    Run.message("Error", "Incorrect type of file");
                    return;
                }

                New();

                line = br.readLine();

                while(line.compareTo("*END*") != 0) {

                        // Loading Points
                    if(line.compareTo("*Point*") == 0) {

                        line = br.readLine();

                        String name = "";

                        if (line.compareTo("Start") == 0) {

                            name = br.readLine();
                            Double value = Double.parseDouble(br.readLine());
                            double x = Double.parseDouble(br.readLine());
                            double y = Double.parseDouble(br.readLine());

                            Point point = new Start_Point(name, value);

                            canvas.add_point(point, x, y);

                        } else if (line.compareTo("End") == 0) {

                            name = br.readLine();
                            Double value = Double.parseDouble(br.readLine());
                            double x = Double.parseDouble(br.readLine());
                            double y = Double.parseDouble(br.readLine());

                            Point point = new End_Point(name, value);

                            canvas.add_point(point, x, y);

                        }

                        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Points"));

                        line = br.readLine();

                        // Loading Blocks
                    } else if(line.compareTo("*Block*") == 0) {

                        line = br.readLine();

                        String name;
                        double x;
                        double y;

                        Block block;

                        if (line.compareTo("ADD") == 0) {

                            name = br.readLine();
                            x = Double.parseDouble(br.readLine());
                            y = Double.parseDouble(br.readLine());

                            block = new ADD_Block(name);

                        } else if (line.compareTo("SUB") == 0) {

                            name = br.readLine();
                            x = Double.parseDouble(br.readLine());
                            y = Double.parseDouble(br.readLine());

                            block = new SUB_Block(name);

                        } else if (line.compareTo("MUL") == 0) {

                            name = br.readLine();
                            x = Double.parseDouble(br.readLine());
                            y = Double.parseDouble(br.readLine());

                            block = new MUL_Block(name);

                        } else { //if (line.compareTo("DIV") == 0) {

                            name = br.readLine();
                            x = Double.parseDouble(br.readLine());
                            y = Double.parseDouble(br.readLine());

                            block = new DIV_Block(name);

                        }

                        canvas.add_block(block, x, y);

                        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));

                        line = br.readLine();

                    } else if(line.compareTo("*Connection*") == 0) {

                        String name = br.readLine();
                        String output_name = br.readLine();
                        String input_name = br.readLine();

                        Block input_block = Block.Blocks.get(input_name);
                        Block output_block = Block.Blocks.get(output_name);

                        OUT_Port output = output_block.AddOutput(UUID.randomUUID().toString());
                        IN_Port input = input_block.AddInput(UUID.randomUUID().toString());

                        Connection connection = new Connection(name, output, input);

                        canvas.add_connection(connection);

                        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Connections"));

                        line = br.readLine();

                    } else {
                        return;
                    }
                }

            } catch(IOException ex) {
                Run.message("Warning", "File to load not found");
            }

            main_gui.stage.setTitle(file.getName());
        }

    }

    /**
     * Clears program from created or loaded schema
     */
    public static void New() {

        TreeItem<String> par = main_gui.leftMenu_items.get("Connections");

        par.getChildren().clear();

        par = main_gui.leftMenu_items.get("Points");

        par.getChildren().clear();

        par = main_gui.leftMenu_items.get("Blocks");

        par.getChildren().clear();

        Block.Blocks.clear();
        Point.Points.clear();
        Connection.Connections.clear();
        Connection.sequence.clear();

        Point.result = null;

        main_gui.canvas.getChildren().clear();

        main_gui.stage.setTitle("Blokove schema");

    }

}
