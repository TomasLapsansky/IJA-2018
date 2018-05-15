package ija.GUI.Display;

import ija.Block.*;
import ija.GUI.handlers;
import ija.GUI.main_gui;
import ija.Port.Connection;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.sqrt;

/**
 * Class for controlling canvas
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class canvas {

    /**
     * Adding block to canvas
     * @param block Reference of Block
     */
    public static void add_block(Block block) {

        block.canvas = new Group();

        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(60);
        rectangle.setWidth(60);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Label label = new Label();
        if(block instanceof ADD_Block)
            label.setText("+" + block.getName());
        else if(block instanceof SUB_Block)
            label.setText("-" + block.getName());
        else if(block instanceof MUL_Block)
            label.setText("*" + block.getName());
        else if(block instanceof DIV_Block)
            label.setText("/" + block.getName());

        stackPane.getChildren().addAll(rectangle, label);

        // Highlighting before choosing the location of new block
        main_gui.topMenu.setDisable(true);
        main_gui.leftMenu.setDisable(true);
        main_gui.canvas.setStyle("-fx-background-color: #eeffff;");

        // Handler for choosing of location
        main_gui.canvas.setOnMouseClicked(e -> {
            block.canvas.setLayoutX(e.getX()-30);
            block.canvas.setLayoutY(e.getY()-30);

            block.canvas.getChildren().add(stackPane);

            main_gui.canvas.getChildren().add(block.canvas);

            main_gui.topMenu.setDisable(false);
            main_gui.leftMenu.setDisable(false);
            main_gui.canvas.setStyle("-fx-background-color: transparent");

            main_gui.canvas.setOnMouseClicked(null);
        });

        // Handler for moving
        block.canvas.setOnMouseDragged(e -> handlers.blockDrag(block, e));

    }

    /**
     * Adding block to canvas from loaded file
     * @param block Reference to block
     * @param x X position
     * @param y Y position
     */
    public static void add_block(Block block, double x, double y) {

        block.canvas = new Group();

        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(60);
        rectangle.setWidth(60);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Label label = new Label();
        if(block instanceof ADD_Block)
            label.setText("+" + block.getName());
        else if(block instanceof SUB_Block)
            label.setText("-" + block.getName());
        else if(block instanceof MUL_Block)
            label.setText("*" + block.getName());
        else if(block instanceof DIV_Block)
            label.setText("/" + block.getName());

        stackPane.getChildren().addAll(rectangle, label);

        block.canvas.setLayoutX(x);
        block.canvas.setLayoutY(y);

        block.canvas.getChildren().add(stackPane);

        main_gui.canvas.getChildren().add(block.canvas);

        // Handler for moving
        block.canvas.setOnMouseDragged(e -> handlers.blockDrag(block, e));

    }

    /**
     * Adding point to canvas
     * @param point Reference of Point
     */
    public static void add_point(Point point) {

        point.canvas = new Group();

        StackPane stackPane = new StackPane();

        Circle circle = new Circle(40);

        if(point instanceof Start_Point)
            circle.setFill(Color.GREEN);
        else if(point instanceof End_Point)
            circle.setFill(Color.RED);

        Label label = new Label(point.getName());
        label.setTextFill(Color.WHITE);

        stackPane.getChildren().addAll(circle, label);

        // Highlighting before choosing the location of new block
        main_gui.topMenu.setDisable(true);
        main_gui.leftMenu.setDisable(true);
        main_gui.canvas.setStyle("-fx-background-color: #eeffff;");

        // Handler for choosing of location
        main_gui.canvas.setOnMouseClicked(e -> {
            point.canvas.setLayoutX(e.getX()-40);
            point.canvas.setLayoutY(e.getY()-40);

            point.canvas.getChildren().add(stackPane);

            main_gui.canvas.getChildren().add(point.canvas);

            main_gui.topMenu.setDisable(false);
            main_gui.leftMenu.setDisable(false);
            main_gui.canvas.setStyle("-fx-background-color: transparent");

            main_gui.canvas.setOnMouseClicked(null);
        });

        // Popup story
        Tooltip.install(point.canvas, new Tooltip(String.valueOf(point.getValue())));

        // Handler for double-click to edit on canvas
        point.canvas.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2) {
                handlers.edit_point(false, point);
            }
        });

        // Handler for moving
        point.canvas.setOnMouseDragged(e -> handlers.pointDrag(point, e));

    }

    /**
     * Adding point to canvas from loaded file
     * @param point Reference of Point
     * @param x X position
     * @param y Y position
     */
    public static void add_point(Point point, double x, double y) {

        point.canvas = new Group();

        StackPane stackPane = new StackPane();

        Circle circle = new Circle(40);

        if(point instanceof Start_Point)
            circle.setFill(Color.GREEN);
        else if(point instanceof End_Point)
            circle.setFill(Color.RED);

        Label label = new Label(point.getName());
        label.setTextFill(Color.WHITE);

        stackPane.getChildren().addAll(circle, label);

        point.canvas.setLayoutX(x);
        point.canvas.setLayoutY(y);

        point.canvas.getChildren().add(stackPane);

        main_gui.canvas.getChildren().add(point.canvas);

        // Popup story
        Tooltip.install(point.canvas, new Tooltip(String.valueOf(point.getValue())));

        // Handler for double-click to edit on canvas
        point.canvas.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2) {
                handlers.edit_point(false, point);
            }
        });

        // Handler for moving
        point.canvas.setOnMouseDragged(e -> handlers.pointDrag(point, e));

    }

    /**
     * Adding connection between two objects on canvas
     * @param connection Reference of Connection
     */
    public static void add_connection(Connection connection) {

        connection.canvas = new Group();

        StackPane stackPane = new StackPane();

        Line line;

        double X1;
        double Y1;
        double X2;
        double Y2;

        // Calculation of image position
        if((connection.getIn_port().getBlock().canvas == null) && (connection.getOut_port().getBlock().canvas == null)){
            X1 = Point.Points.get(connection.getOut_port().getBlock().getName()).canvas.getLayoutX()+40;
            Y1 = Point.Points.get(connection.getOut_port().getBlock().getName()).canvas.getLayoutY()+40;
            X2 = Point.Points.get(connection.getIn_port().getBlock().getName()).canvas.getLayoutX()+40;
            Y2 = Point.Points.get(connection.getIn_port().getBlock().getName()).canvas.getLayoutY()+40;
        } else if(connection.getOut_port().getBlock().canvas == null) {
            X1 = Point.Points.get(connection.getOut_port().getBlock().getName()).canvas.getLayoutX()+40;
            Y1 = Point.Points.get(connection.getOut_port().getBlock().getName()).canvas.getLayoutY()+40;
            X2 = connection.getIn_port().getBlock().canvas.getLayoutX()+30;
            Y2 = connection.getIn_port().getBlock().canvas.getLayoutY()+30;
        } else if(connection.getIn_port().getBlock().canvas == null) {
            X1 = connection.getOut_port().getBlock().canvas.getLayoutX()+30;
            Y1 = connection.getOut_port().getBlock().canvas.getLayoutY()+30;
            X2 = Point.Points.get(connection.getIn_port().getBlock().getName()).canvas.getLayoutX()+40;
            Y2 = Point.Points.get(connection.getIn_port().getBlock().getName()).canvas.getLayoutY()+40;
        } else {
            X1 = connection.getOut_port().getBlock().canvas.getLayoutX()+30;
            Y1 = connection.getOut_port().getBlock().canvas.getLayoutY()+30;
            X2 = connection.getIn_port().getBlock().canvas.getLayoutX()+30;
            Y2 = connection.getIn_port().getBlock().canvas.getLayoutY()+30;
        }

        // Setting gradient to recognize direction of connection
        RadialGradient lg1 = new RadialGradient(0, .1, X1, Y1, (sqrt((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1))), false, CycleMethod.NO_CYCLE, new Stop(0, Color.GREEN), new Stop(1, Color.RED));

        line = new Line(X1, Y1, X2, Y2);
        line.setStrokeWidth(3);
        line.setStroke(lg1);

        stackPane.getChildren().add(line);

        if(X1 <= X2 && Y1 <= Y2) {
            connection.canvas.setLayoutX(X1);
            connection.canvas.setLayoutY(Y1);
        } else if(X1 >= X2 && Y1 <= Y2) {
            connection.canvas.setLayoutX(X2);
            connection.canvas.setLayoutY(Y1);
        } else if(X1 <= X2 && Y1 >= Y2) {
            connection.canvas.setLayoutX(X1);
            connection.canvas.setLayoutY(Y2);
        } else if(X1 >= X2 && Y1 >= Y2) {
            connection.canvas.setLayoutX(X2);
            connection.canvas.setLayoutY(Y2);
        }

        connection.canvas.getChildren().add(stackPane);

        // Popup story
        Tooltip.install(line, new Tooltip(connection.getName() + " " + connection.getIn_port().getValue()));

        // Handler for double-click to edit on canvas
        line.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2) {
                handlers.edit_connection(false, connection);
            }
        });

        main_gui.canvas.getChildren().add(connection.canvas);
        connection.canvas.toBack();

    }

    /**
     * Removing block from canvas
     * @param block Reference of Block
     */
    public static void remove_block(Block block) {

        main_gui.canvas.getChildren().remove(block.canvas);

    }

    /**
     * Removing point from canvas
     * @param point Reference of Point
     */
    public static void remove_point(Point point) {

        main_gui.canvas.getChildren().remove(point.canvas);

    }

    /**
     * Removing connection from canvas
     * @param connection Reference of Connection
     */
    public static void remove_connection(Connection connection) {

        main_gui.canvas.getChildren().remove(connection.canvas);

    }

}