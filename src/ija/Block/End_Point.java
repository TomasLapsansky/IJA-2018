package ija.Block;

import ija.Port.Connection;
import ija.Port.IN_Port;

public class End_Point extends Point {

    public IN_Port port;

    public End_Point(String name, double value) {
        super(name, value);
        result = this;

        port = new IN_Port(name);

        this.gate_block.AddOutput(name);
        Connection connection = new Connection(name, this.gate_block.getOutput(name), port);
        connection.gate = true;
    }

}
