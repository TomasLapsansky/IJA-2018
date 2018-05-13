package ija.Block;

import ija.Port.Connection;
import ija.Port.OUT_Port;

public class Start_Point extends Point {

    public OUT_Port port;

    public Start_Point(String name, double value) {
        super(name, value);

        port = new OUT_Port(name);

        this.gate_block.AddInput(name);
        Connection connection = new Connection(name, port, this.gate_block.getInput(name));
        connection.gate = true;
    }
}
