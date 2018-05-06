package ija.Block;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public abstract class Point {

    public static Map<String, Point> Points;
    public static Point result;
    public String name;
    public int value;

    public Block gate_block;

    public Point(String name, int value, Group g) {

        this.name = name;
        this.value = value;
        Points.put(name, this);

        this.gate_block = new ADD_Block(name, g);
    }
}
