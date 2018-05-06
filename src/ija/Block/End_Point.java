package ija.Block;

import ija.Port.IN_Port;

public class End_Point extends Point {

    public IN_Port result;

    public End_Point(String name, int value) {
        super(name, value);
        result = null;
    }
}
