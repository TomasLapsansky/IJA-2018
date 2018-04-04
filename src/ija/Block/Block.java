package ija.Block;

import ija.Port.*;

import java.util.*;

abstract public class Block {

    private String name;
    protected ArrayList<Port> PortIN;
    protected ArrayList<Port> PortOUT;

    public Block(String name) {

        this.name = name;
        PortIN = new ArrayList<Port>();
        PortOUT = new ArrayList<Port>();

    }

    public String getName() {

        return this.name;

    }

    public void Rename(String name) {

        this.name = name;

    }

    public void AddInput(String name, double value) {

        Port input = new IN_Port(name, value);
        PortIN.add(input);

    }

    public void RemoveInput(String name) {

        Port remove = new IN_Port(name, 0.0);
        PortIN.remove(remove);

    }

    public void AddOutput(String name, double value) {

        Port output = new OUT_Port(name, value);
        PortOUT.add(output);

    }

    public void RemoveOutput(String name) {

        Port output = new OUT_Port(name, 0.0);
        PortOUT.remove(output);

    }

    public abstract void result();

}
