package ija.GUI;

import ija.Block.ADD_Block;
import ija.Block.DIV_Block;
import ija.Block.MUL_Block;
import ija.Block.SUB_Block;
import ija.GUI.Display.*;
import ija.IO;
import ija.Port.IN_Port;

import java.util.Stack;
import java.util.UUID;

import ija.Block.*;
import ija.Port.*;
import ija.Run;

public class handlers {

    public static void file_new() {

        IO.New();

    }

    public static void file_open() {

        IO.load();

    }

    public static void file_save() {

        IO.save();

    }

    public static void file_settings() {

    }

    public static void file_exit() {
        System.exit(0);
    }

    public static void edit_point() {

        Point point = selectors.point();

        // TODO

    }

    public static void edit_connection() {

        // Connection values name are random

        Connection connection = selectors.connection();

        Block original_input_block = connection.getIn_port().getBlock();
        Block original_output_block = connection.getOut_port().getBlock();

        String ret[];
        ret = edit_connection.display(connection); // return name, input, output

        if (ret[0] == null || ret[1] == null || ret[2] == null || ret[0].isEmpty() || ret[1].isEmpty() || ret[2].isEmpty()) {
            return;
        }

        // End if there are no edits
        if((original_input_block.getName().equals(ret[1])) && (original_output_block.getName().equals(ret[2])))
            return;

        // Remove of previous ports
        connection.getIn_port().remove();
        connection.getOut_port().remove();

        // Init of new ports
        Block input_block = Block.Blocks.get(ret[1]);
        Block output_block = Block.Blocks.get(ret[2]);

        OUT_Port output = input_block.AddOutput(UUID.randomUUID().toString());
        IN_Port input = output_block.AddInput(UUID.randomUUID().toString());

        // Setting new ports
        connection.setOut_port(output);
        connection.setIn_port(input);
    }

    public static void add_point() {

        String type = selectors.setPoint();

        if(type == null || type.isEmpty())
            return;

        String ret[];
        ret = add_point.display();

        if(ret[0] == null || ret[1] == null || ret[0].isEmpty() || ret[1].isEmpty())
            return;

        if(type.equals("Start")) {

            Start_Point point = new Start_Point(ret[0], Double.parseDouble(ret[1]));

            canvas.add_point(point);

        } else if(type.equals("End")) {

            End_Point point = new End_Point(ret[0], Double.parseDouble(ret[1]));

            canvas.add_point(point);

        } else {
            return;
        }

        main_gui.makeBranch(ret[0], main_gui.leftMenu_items.get("Points"));

    }

    public static void add_add() {
        String name = add_block.display();
        if (name.isEmpty()) {
            return;
        }

        Block block = new ADD_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_sub() {
        String name = add_block.display();
        if (name.isEmpty()) {
            return;
        }

        Block block = new SUB_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_mul() {
        String name = add_block.display();
        if (name.isEmpty()) {
            return;
        }

        Block block = new MUL_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_div() {
        String name = add_block.display();
        if (name == null || name.isEmpty()) {
            return;
        }

        Block block = new DIV_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_connection() {

        // Connection values name are random

        String ret[];
        ret = add_connection.display(); // return name, input, output

        if (ret[0] == null || ret[1] == null || ret[2] == null || ret[0].isEmpty() || ret[1].isEmpty() || ret[2].isEmpty()) {
            return;
        }

        String name = ret[0];

        Block input_block = Block.Blocks.get(ret[1]);
        Block output_block = Block.Blocks.get(ret[2]);

        OUT_Port output = output_block.AddOutput(UUID.randomUUID().toString());
        IN_Port input = input_block.AddInput(UUID.randomUUID().toString());

        Connection connection = new Connection(name, output, input);

        canvas.add_connection(connection);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Connections"));

    }

    public static void delete_point() {

    }

    public static void delete_block() {

        Block select = selectors.block();

        // Using stack because of ConcurrentModificationException

        Stack<IN_Port> IN = new Stack<>();
        Stack<OUT_Port> OUT = new Stack<>();

        if (!(select.PortIN.isEmpty())) {
            for (IN_Port port : select.PortIN) {
                IN.push(port);
            }
        }
        if (!(select.PortOUT.isEmpty())) {
            for (OUT_Port port : select.PortOUT) {
                OUT.push(port);
            }
        }

        while(!(IN.empty()))
            IN.pop().remove();

        while(!(OUT.empty()))
            OUT.pop().remove();

        Block.Blocks.remove(select.getName());

        canvas.remove_block(select);

        main_gui.removeBranch(select.getName(), "Blocks");

    }

    public static void delete_connection() {

        Connection select = selectors.connection();

        String name = select.getName();

        // Musi nastavit connections v blokoch na null
        select.getIn_port().setConnection(null);
        select.getOut_port().setConnection(null);

        // Vymazat z in bloku
        select.getIn_port().getBlock().RemoveInput(name);

        // Vymazat z out bloku
        select.getOut_port().getBlock().RemoveOutput(name);

        // Vymazat zo zoznamu
        Connection.Connections.remove(name);

        // GUI
        main_gui.removeBranch(name, "Connections");
        canvas.remove_connection(select);

    }

    public static void run_cycleDetection() {

        Run.cycle_detection(true);
    }

    public static void run_run() {

        Run.run();

        Run.message("Final", "Value is " + Point.result.value);

    }

    public static void debug() {

        System.out.println("GATE");

        for (String key: Point.Points.keySet()) {

            Block block = Point.Points.get(key).gate_block;

            System.out.println(block.getName());

            for (Port port: block.PortIN) {
                System.out.println(port.getName());
                System.out.println(port.getValue());
            }

            for (Port port: block.PortOUT) {
                System.out.println(port.getName());
                System.out.println(port.getValue());
            }

        }

        System.out.println("BLOCKS");

        for (String key: Block.Blocks.keySet()) {

            Block block = Block.Blocks.get(key);

            System.out.println(block.getName());

            for (Port port: block.PortIN) {
                System.out.println(port.getName());
                System.out.println(port.getValue());
            }

            for (Port port: block.PortOUT) {
                System.out.println(port.getName());
                System.out.println(port.getValue());
            }

        }

        System.out.println("CONNECTIONS");

        for (String key: Connection.Connections.keySet()) {

            Connection connection = Connection.Connections.get(key);

            System.out.println(connection.getName());

            System.out.println("INPORT");

        }

    }
}

