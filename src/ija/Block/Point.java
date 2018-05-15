package ija.Block;

import javafx.scene.Group;
import javafx.scene.control.Tooltip;

import java.util.Map;

/**
 * Abstract Point class for inputs and output of program
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public abstract class Point {

    public static Map<String, Point> Points;
    public static End_Point result;
    private String name;
    private double value;

    public Block gate_block;

    public Group canvas;

    /**
     * Constructor
     * @param name Name of Point
     * @param value Value of Point
     */
    public Point(String name, double value) {

        this.name = name;
        this.value = value;
        Points.put(name, this);

        this.gate_block = new ADD_Block(name);
        this.gate_block.gate = true;
    }

    /**
     * Getter
     * @return Name of Point
     */
    public String getName() {
        return name;
    }

    /**
     * Setter
     * @param name New name of Point
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter
     * @return Value of Point
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter
     * @param value Value of Point
     */
    public void setValue(double value) {
        this.value = value;

        // Actualize
        Tooltip.install(this.canvas, new Tooltip(String.valueOf(this.getValue())));
    }
}
