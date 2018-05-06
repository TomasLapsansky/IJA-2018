package ija.Block;

import ija.Port.Connection;
import ija.Port.IN_Port;

public class End_Point extends Point {

    public IN_Port port;

    public End_Point(String name, int value) {
        super(name, value);
        result = this;

        port = new IN_Port(name);

        this.gate_block.AddOutput(name);
        new Connection(name, this.gate_block.getOutput(name), port);
    }

}
