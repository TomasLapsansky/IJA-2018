package ija.Block;

import ija.GUI.main_gui;
import ija.Port.*;
import javafx.scene.Group;

import java.util.*;

/**
 * Abstract block class for block implementation
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
abstract public class Block {

    private String name;
    public ArrayList<IN_Port> PortIN;
    public ArrayList<OUT_Port> PortOUT;
    public static Map<String, Block> Blocks;
    public boolean gate;
    public Group canvas;

    public Map<String, Boolean> connections_set;

    /**
     * Constructor
     * @param name name of Block
     */
    public Block(String name) {

        this.name = name;

        PortIN = new ArrayList<>();
        PortOUT = new ArrayList<>();
        connections_set = new HashMap<>();
        gate = false;
        canvas = null;

        Blocks.put(name, this);

    }

    /**
     * Getter
     * @return Block name
     */
    public String getName() {

        return this.name;

    }

    /**
     * Setter
     * @param name New name
     */
    public void Rename(String name) {

        this.name = name;

    }

    /**
     * Add new input port
     * @param name Name of port
     * @return Reference of port
     */
    public IN_Port AddInput(String name) {

        IN_Port input = new IN_Port(name, this);
        PortIN.add(input);
        connections_set.put(name, false);

        return input;
    }

    /**
     * Remove input port
     * @param name Name of port
     */
    public void RemoveInput(String name) {

        IN_Port in = null;

        for (IN_Port aPortIN : PortIN) {
            if (aPortIN.getName().equals(name))
                in = aPortIN;
        }

        if(in == null)
            return;

        Connection connection = in.getConnection();

        PortIN.remove(in);
        connections_set.remove(in.getName());

        if(connection != null) {
            // Set connection for other port
            connection.getOut_port().setConnection(null);
            connection.getOut_port().remove();

            Connection.Connections.remove(connection.getName());
            System.out.println(connection.getName());

            main_gui.canvas.getChildren().remove(connection.canvas);

            main_gui.removeBranch(connection.getName(), "Connections");
        }

    }

    /**
     * Add new output port
     * @param name Name of port
     * @return Reference
     */
    public OUT_Port AddOutput(String name) {

        OUT_Port output = new OUT_Port(name, this);
        PortOUT.add(output);

        return output;
    }

    /**
     * Remove output port
     * @param name Name of port
     */
    public void RemoveOutput(String name) {

        OUT_Port out = null;

        for (OUT_Port aPortOUT : PortOUT) {
            if (aPortOUT.getName().equals(name))
                out = aPortOUT;
        }

        if(out == null)
            return;

        Connection connection = out.getConnection();

        PortOUT.remove(out);

        if(connection != null) {
            // Set connection for other port
            connection.getIn_port().setConnection(null);
            connection.getIn_port().remove();

            Connection.Connections.remove(connection.getName());

            main_gui.canvas.getChildren().remove(connection.canvas);

            main_gui.removeBranch(connection.getName(), "Connections");
        }

    }

    /**
     * Getter for ArrayList of input ports
     * @param name Name of port
     * @return Reference
     */
    public IN_Port getInput(String name) {

        for (IN_Port input: PortIN) {
            if(input.getName().equals(name))
                return input;
        }

        return null;

    }

    /**
     * Getter for ArrayList of output ports
     * @param name Name of port
     * @return Reference
     */
    public OUT_Port getOutput(String name) {

        for (OUT_Port output: PortOUT) {
            if(output.getName().equals(name))
                return output;
        }

        return null;

    }

    /**
     * Abstract class for result of child
     */
    public abstract void result();

}
