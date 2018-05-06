package ija.Block;

import ija.Port.OUT_Port;

public class Start_Point extends Point {

    public OUT_Port port;

    public Start_Point(String name, int value) {
        super(name, value);
        port = null;
    }
}
