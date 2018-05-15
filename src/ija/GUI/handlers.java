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
import javafx.scene.input.MouseEvent;

/**
 * Handlers for Graphic user Interface
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class handlers {

    /**
     * Creates new project
     */
    static void file_new() {

        IO.New();

    }

    /**
     * Opens existing project
     */
    static void file_open() {

        IO.load();

    }

    /**
     * Saves project
     */
    static void file_save() {

        IO.save();

    }

    /**
     * Future implementation of settings
     * @ TODO: 15/05/2018 Implements
     */
    static void file_settings() {

    }

    /**
     * Exiting of program
     */
    static void file_exit() {
        System.exit(0);
    }

    /**
     * Select and edit selected point
     * @param selector Bool determinate if we have to call selector
     * @param point Reference to Point / null if selector = true
     */
    public static void edit_point(boolean selector, Point point) {

        if(selector)
            point = selectors.point(false);

        if(point == null) {
            return;
        }

        String ret = edit_point.display(point);

        if(ret == null || ret.isEmpty()) {
            return;
        }

        point.setValue(Double.parseDouble(ret));
    }

    /**
     * Select and edit selected connection
     * @param selector Bool determinate if we have to call selector
     * @param connection Reference to Connection / null if selector = true
     */
    public static void edit_connection(boolean selector, Connection connection) {

        // Connection values name are random

        if(selector)
            connection = selectors.connection();

        if(connection == null) {
            return;
        }

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

    /**
     * Selects type of Point (Start/End) and creates its instance + add it to schema
     */
    static void add_point() {

        String type = selectors.setPoint();

        if(type == null || type.isEmpty()) {
            return;
        }

        if(type.equals("End") && Point.result != null) {
            Run.message("Warning", "You have to remove End Point to add another");
            return;
        }

        String ret[];
        ret = add_point.display();

        if(ret[0] == null || ret[1] == null || ret[0].isEmpty() || ret[1].isEmpty()) {
            return;
        }

        String name = ret[0];
        double value = Double.parseDouble(ret[1]);

        if(nameCheck(name))
            return;

        switch (type) {
            case "Start": {

                Start_Point point = new Start_Point(name, value);

                canvas.add_point(point);

                break;
            }
            case "End": {

                End_Point point = new End_Point(name, value);

                canvas.add_point(point);

                break;
            }
            default:
                return;
        }

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Points"));

    }

    /**
     * Calls display function and creates instance of ADD block
     */
    static void add_add() {
        String name = add_block.display();
        if (name == null || name.isEmpty()) {
            return;
        }

        if(nameCheck(name))
            return;

        Block block = new ADD_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    /**
     * Calls display function and creates instance of SUB block
     */
    static void add_sub() {
        String name = add_block.display();
        if (name == null || name.isEmpty()) {
            return;
        }

        if(nameCheck(name))
            return;

        Block block = new SUB_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    /**
     * Calls display function and creates instance of MUL block
     */
    static void add_mul() {
        String name = add_block.display();
        if (name == null || name.isEmpty()) {
            return;
        }

        if(nameCheck(name))
            return;

        Block block = new MUL_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    /**
     * Calls display function and creates instance of DIV block
     */
    static void add_div() {
        String name = add_block.display();
        if (name == null || name.isEmpty()) {
            return;
        }

        if(nameCheck(name))
            return;

        Block block = new DIV_Block(name);

        canvas.add_block(block);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    /**
     * Calls display function and creates instance of connection
     */
    static void add_connection() {

        // Connection values name are random

        String ret[];
        ret = add_connection.display(); // return name, input, output

        if (ret[0] == null || ret[1] == null || ret[2] == null || ret[0].isEmpty() || ret[1].isEmpty() || ret[2].isEmpty()) {
            return;
        }

        String name = ret[0];

        if(nameCheck(name))
            return;

        Block input_block = Block.Blocks.get(ret[1]);
        Block output_block = Block.Blocks.get(ret[2]);

        OUT_Port output = output_block.AddOutput(UUID.randomUUID().toString());
        IN_Port input = input_block.AddInput(UUID.randomUUID().toString());

        Connection connection = new Connection(name, output, input);

        canvas.add_connection(connection);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Connections"));


    }

    /**
     * Delete point and connections connected to it
     */
    static void delete_point() {

        Point select = selectors.point(true);
        if(select == null) {
            return;
        }

        Block block = select.gate_block;
        Connection connection = block.PortIN.get(0).getConnection();

        Point.Points.remove(select.getName());
        Connection.Connections.remove(connection.getName());
        Block.Blocks.remove(block.getName());

        Stack<OUT_Port> OUT = new Stack<>();

        if(!(block.PortIN.isEmpty())) {
            for (OUT_Port port : block.PortOUT) {
                OUT.push(port);
            }
        }

        while(!(OUT.empty())) {
            OUT.pop().remove();
        }

        if(Point.result == select) {
            Point.result = null;
        }

        canvas.remove_point(select);

        main_gui.removeBranch(select.getName(), "Points");

    }

    /**
     * Delete block and connections connected to it
     */
    static void delete_block() {

        Block select = selectors.block();
        if(select == null) {
            return;
        }

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

    /**
     * Delete connections and connected ports
     */
    static void delete_connection() {

        Connection select = selectors.connection();

        if(select == null) {
            return;
        }

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

    /**
     * Runs Detection of Cycles with warning message
     */
    static void run_cycleDetection() {

        Run.cycle_detection(true);
    }

    /**
     * Runs run algorithm
     */
    static void run_run() {

        Run.run();

        Run.message("Final", "Value is " + Point.result.getValue());

    }

    /**
     * Checks uniqueness of names of created objects
     * @param name Name of created object
     * @return True / False = Non-unique / Unique
     */
    private static boolean nameCheck(String name) {

        for (String key: Block.Blocks.keySet()) {
            if(key.equals(name)){
                Run.message("Warning", "Object with the same name already exists");
                return true;
            }
        }

        for (String key: Connection.Connections.keySet()) {
            if(key.equals(name)){
                Run.message("Warning", "Object with the same name already exists");
                return true;
            }
        }

        return false;
    }

    /**
     * Handles movement of blocks at canvas
     * @param block Reference of Block
     * @param event Type of Event(MouseDrag)
     */
    public static void blockDrag(Block block, MouseEvent event) {

        double x,y;
        double paneX, paneY;

        paneX = main_gui.canvas.getWidth();
        paneY = main_gui.canvas.getHeight();

        x = event.getX() + block.canvas.getLayoutX() - 30;
        y = event.getY() + block.canvas.getLayoutY() - 30;

        if (x >= 0.0 && x + 60 <= paneX)
            block.canvas.setLayoutX(x);
        else if(x < 0.0)
            block.canvas.setLayoutX(0);
        else
            block.canvas.setLayoutX(paneX - 60);

        if (y >= 0.0 && y + 60 <= paneY)
            block.canvas.setLayoutY(y);
        else if(y < 0.0)
            block.canvas.setLayoutY(0);
        else
            block.canvas.setLayoutY(paneY - 60);


        for (Port port : block.PortIN) {

            canvas.remove_connection(port.getConnection());
            canvas.add_connection(port.getConnection());
        }

        for (Port port : block.PortOUT) {

            canvas.remove_connection(port.getConnection());
            canvas.add_connection(port.getConnection());
        }
    }

    /**
     * Handles movement of points at canvas
     * @param point Reference of Point
     * @param event Type of Event(MouseDrag)
     */
    public static void pointDrag(Point point, MouseEvent event) {

        double x,y;
        double paneX, paneY;

        paneX = main_gui.canvas.getWidth();
        paneY = main_gui.canvas.getHeight();

        x = event.getX() + point.canvas.getLayoutX() - 40;
        y = event.getY() + point.canvas.getLayoutY() - 40;

        if (x >= 0.0 && x + 80 <= paneX)
            point.canvas.setLayoutX(x);
        else if(x < 0.0)
            point.canvas.setLayoutX(0);
        else
            point.canvas.setLayoutX(paneX - 80);

        if (y >= 0.0 && y + 80 <= paneY)
            point.canvas.setLayoutY(y);
        else if(y < 0.0)
            point.canvas.setLayoutY(0);
        else
            point.canvas.setLayoutY(paneY - 80);


        if(point instanceof End_Point) {

            for (Port port : point.gate_block.PortIN) {

                canvas.remove_connection(port.getConnection());
                canvas.add_connection(port.getConnection());
            }

        } else if(point instanceof Start_Point) {

            for (Port port : point.gate_block.PortOUT) {

                canvas.remove_connection(port.getConnection());
                canvas.add_connection(port.getConnection());
            }
        }
    }

    /**
     * Internal function for debugging of references
     */
    static void debug() {

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

