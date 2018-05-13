package ija.GUI.Display;

import ija.Block.*;
import ija.GUI.main_gui;
import ija.Port.Connection;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.sqrt;

public class canvas {

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

        main_gui.canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                block.canvas.setLayoutX(event.getX()-30);
                block.canvas.setLayoutY(event.getY()-30);

                block.canvas.getChildren().add(stackPane);

                main_gui.canvas.getChildren().add(block.canvas);

                main_gui.canvas.setOnMouseClicked(null);
            }
        });

    }

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

    }

    public static void add_point(Point point) {

        point.canvas = new Group();

        StackPane stackPane = new StackPane();

        Circle circle = new Circle(40);

        if(point instanceof Start_Point)
            circle.setFill(Color.GREEN);
        else if(point instanceof End_Point)
            circle.setFill(Color.RED);

        Label label = new Label(point.name);
        label.setTextFill(Color.WHITE);

        stackPane.getChildren().addAll(circle, label);

        main_gui.canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                point.canvas.setLayoutX(event.getX()-40);
                point.canvas.setLayoutY(event.getY()-40);

                point.canvas.getChildren().add(stackPane);

                main_gui.canvas.getChildren().add(point.canvas);

                main_gui.canvas.setOnMouseClicked(null);
            }
        });

    }

    public static void add_point(Point point, double x, double y) {

        point.canvas = new Group();

        StackPane stackPane = new StackPane();

        Circle circle = new Circle(40);

        if(point instanceof Start_Point)
            circle.setFill(Color.GREEN);
        else if(point instanceof End_Point)
            circle.setFill(Color.RED);

        Label label = new Label(point.name);
        label.setTextFill(Color.WHITE);

        stackPane.getChildren().addAll(circle, label);

        point.canvas.setLayoutX(x);
        point.canvas.setLayoutY(y);

        point.canvas.getChildren().add(stackPane);

        main_gui.canvas.getChildren().add(point.canvas);

    }

    public static void add_connection(Connection connection) {

        connection.canvas = new Group();

        StackPane stackPane = new StackPane();

        Line line;

        double X1;
        double Y1;
        double X2;
        double Y2;

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

        RadialGradient lg1 = new RadialGradient(0, .1, X1, Y1, (sqrt((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1))), false, CycleMethod.NO_CYCLE, new Stop(0, Color.GREEN), new Stop(1, Color.RED));

        line = new Line(X1, Y1, X2, Y2);
        line.setStrokeWidth(3);
        line.setStroke(lg1);

        //Label label = new Label(connection.getName());

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

        Tooltip.install(line, new Tooltip(connection.getName()));   // GOD

        main_gui.canvas.getChildren().add(connection.canvas);
        connection.canvas.toBack();

    }

    public static void remove_block(Block block) {

        main_gui.canvas.getChildren().remove(block.canvas);

    }

    public static void remove_point(Point point) {

        main_gui.canvas.getChildren().remove(point.canvas);

    }

    public static void remove_connection(Connection connection) {

        main_gui.canvas.getChildren().remove(connection.canvas);

    }

}
