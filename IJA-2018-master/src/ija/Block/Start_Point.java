package ija.Block;

import ija.Port.Connection;
import ija.Port.OUT_Port;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Start_Point extends Point {

    public OUT_Port port;

    public Start_Point(String name, int value, Group g, Line ciara) {
        super(name, value, g);

        port = new OUT_Port(name);

        this.gate_block.AddInput(name);
        new Connection(name, port, this.gate_block.getInput(name), ciara);
    }
}
