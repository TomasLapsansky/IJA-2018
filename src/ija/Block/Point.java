package ija.Block;

import java.util.Map;

public abstract class Point {

    public static Map<String, Point> points;
    public String name;
    public int value;

    public Point(String name, int value) {

        this.name = name;
        this.value = value;
    }
}
