package ija.Block;

import java.util.Map;

public abstract class Point {

    public static Map<String, Point> Points;
    public static End_Point result;
    public String name;
    public double value;

    public Block gate_block;

    public Point(String name, double value) {

        this.name = name;
        this.value = value;
        Points.put(name, this);

        this.gate_block = new ADD_Block(name);
        this.gate_block.gate = true;
    }
}
