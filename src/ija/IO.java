package ija;

import ija.Block.*;
import ija.GUI.main_gui;
import ija.Port.Connection;
import ija.Port.IN_Port;
import ija.Port.OUT_Port;
import javafx.scene.control.TreeItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.UUID;

public class IO {

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

                    writer.println(point.name);
                    writer.println(point.value);

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
                    }
                }

                // Connections

                for (String key: Connection.Connections.keySet()) {

                    Connection connection = Connection.Connections.get(key);

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
        }

    }

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

                            new Start_Point(name, value);

                        } else if (line.compareTo("End") == 0) {

                            name = br.readLine();
                            Double value = Double.parseDouble(br.readLine());

                            new End_Point(name, value);

                        }

                        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Points"));

                        line = br.readLine();

                        // Loading Blocks
                    } else if(line.compareTo("*Block*") == 0) {

                        line = br.readLine();

                        String name = "";

                        if (line.compareTo("ADD") == 0) {

                            name = br.readLine();

                            new ADD_Block(name);

                        } else if (line.compareTo("SUB") == 0) {

                            name = br.readLine();

                            new SUB_Block(name);

                        } else if (line.compareTo("MUL") == 0) {

                            name = br.readLine();

                            new MUL_Block(name);

                        } else if (line.compareTo("DIV") == 0) {

                            name = br.readLine();

                            new DIV_Block(name);

                        }

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

                        new Connection(name, output, input);

                        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Connections"));

                        line = br.readLine();

                    } else {
                        return;
                    }
                }

            } catch(IOException ex) {
                Run.message("Warning", "File to load not found");
            }

        }

    }

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

        Point.result = null;

    }

}
