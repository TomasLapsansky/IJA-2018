package ija.Block;

import ija.Port.*;

import java.util.*;

abstract public class Block {

    private String name;
    protected ArrayList<IN_Port> PortIN;
    protected ArrayList<OUT_Port> PortOUT;

    public Block(String name) {

        this.name = name;
        PortIN = new ArrayList<IN_Port>();
        PortOUT = new ArrayList<OUT_Port>();

    }

    public String getName() {

        return this.name;

    }

    public void Rename(String name) {

        this.name = name;

    }

    public void AddInput(String name, double value) {

        IN_Port input = new IN_Port(name, value);
        PortIN.add(input);

    }

    public void RemoveInput(String name) {

        Port remove = new IN_Port(name, 0.0);
        PortIN.remove(remove);

    }

    public void AddOutput(String name) {

        OUT_Port output = new OUT_Port(name);
        PortOUT.add(output);

    }

    public void RemoveOutput(String name) {

        Port output = new OUT_Port(name);
        PortOUT.remove(output);

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
