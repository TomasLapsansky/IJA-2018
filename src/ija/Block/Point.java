package ija.Block;

import javafx.scene.Group;
import javafx.scene.control.Tooltip;

import java.util.Map;

public abstract class Point {

    public static Map<String, Point> Points;
    public static End_Point result;
    private String name;
    private double value;

    public Block gate_block;

    public Group canvas;

    public Point(String name, double value) {

        this.name = name;
        this.value = value;
        Points.put(name, this);

        this.gate_block = new ADD_Block(name);
        this.gate_block.gate = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;

        // Actualize
        Tooltip.install(this.canvas, new Tooltip(String.valueOf(this.getValue())));
    }
}
