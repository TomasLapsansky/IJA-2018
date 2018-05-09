package ija.Block;

import ija.GUI.main_gui;
import ija.Port.*;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.*;

abstract public class Block {

    private String name;
    public ArrayList<IN_Port> PortIN;
    public ArrayList<OUT_Port> PortOUT;
    public Group CanvasPoss;
    public static Map<String, Block> Blocks;

    public Block(String name, Group rect) {

        this.name = name;
        this.CanvasPoss = rect;

        PortIN = new ArrayList<>();
        PortOUT = new ArrayList<>();

        Blocks.put(name, this);

    }

    public String getName() {

        return this.name;

    }

    public void Rename(String name) {

        this.name = name;

    }

    public Group getCanvasPoss(){

        return this.CanvasPoss;

    }

    public IN_Port AddInput(String name) {

        IN_Port input = new IN_Port(name, this);
        PortIN.add(input);

        return input;
    }

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

        if(connection != null) {
            // Nastavi connection pre opacny port na null
            connection.getOut_port().setConnection(null);
            connection.getOut_port().remove();

            Connection.Connections.remove(connection.getName());
            System.out.println(connection.getName());
            main_gui.removeBranch(connection.getName(), "Connections");
        }

    }

    public OUT_Port AddOutput(String name) {

        OUT_Port output = new OUT_Port(name, this);
        PortOUT.add(output);

        return output;
    }

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
            // Nastavi connection pre opacny port na null
            connection.getIn_port().setConnection(null);
            connection.getIn_port().remove();

            Connection.Connections.remove(connection.getName());
            main_gui.removeBranch(connection.getName(), "Connections");
        }

        /*
        Port output = new OUT_Port(name, this);
        PortOUT.remove(output);

        Connection connection = output.getConnection();
        if(connection != null) {
            // Nastavi connection pre opacny port na null
            connection.getIn_port().setConnection(null);
            connection.getIn_port().remove();

            Connection.Connections.remove(connection.getName());
            main_gui.removeBranch(connection.getName(), "Connections");
        }
         */

    }

    //Podpora pre hlbsi rozvoj programu
    public IN_Port getInput(String name) {

        for (IN_Port input: PortIN) {
            if(input.getName().equals(name))
                return input;
        }

        return null;

    }

    public OUT_Port getOutput(String name) {

        for (OUT_Port output: PortOUT) {
            if(output.getName().equals(name))
                return output;
        }

        return null;

    }

    public abstract void result();

}
