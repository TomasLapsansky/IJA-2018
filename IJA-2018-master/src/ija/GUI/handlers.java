package ija.GUI;

import ija.Block.ADD_Block;
import ija.Block.DIV_Block;
import ija.Block.MUL_Block;
import ija.Block.Point;
import ija.Block.SUB_Block;
import ija.GUI.Display.*;
import ija.Port.IN_Port;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

import ija.Block.*;
import ija.Port.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class handlers {

    public static void file_new() {

    }

    public static void file_open() {

    }

    public static void file_save() {

    }

    public static void file_settings() {

    }

    public static void file_exit() {
        System.exit(0);
    }

    public static void edit_point() {

        Point point = selectors.point();

    }

    /*
    public static void edit_block() {

    }
    */

    public static void edit_connection() {

        // Connection values name are random

        Connection connection = selectors.connection();

        Block original_input_block = connection.getIn_port().getBlock();
        Block original_output_block = connection.getOut_port().getBlock();

        String ret[];
        ret = edit_connection.display(connection); // return name, input, output

        if (ret[0] == null || ret[1] == null || ret[2] == null || ret[0].isEmpty() || ret[1].isEmpty() || ret[2].isEmpty()) {
            return;
        }

        // End if there are no edits
        if((original_input_block.getName().equals(ret[1])) && (original_output_block.getName().equals(ret[2])))
            return;

        // Remove of previous ports
        connection.getIn_port().remove();
        connection.getOut_port().remove();

        // Init of new ports
        Block input_block = Block.Blocks.get(ret[1]);
        Block output_block = Block.Blocks.get(ret[2]);

        OUT_Port output = input_block.AddOutput(UUID.randomUUID().toString());
        IN_Port input = output_block.AddInput(UUID.randomUUID().toString());

        // Setting new ports
        connection.setOut_port(output);
        connection.setIn_port(input);
    }

    public static void add_point() {

        String type = selectors.setPoint();

        if(type.isEmpty())
            return;

        String ret[];
        ret = add_point.display();


        Group Blok = new Group();
        Line ciara = new Line();

        Rectangle rect = new Rectangle(60,60);
        rect.setFill(Color.GREEN);
        Label AddName = new Label(ret[0]);
        AddName.setLayoutX(25);
        AddName.setLayoutY(25);
        AddName.setTextFill(Color.BLACK);

        Blok.getChildren().addAll(rect, AddName);

        Blok.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Blok.setLayoutX(event.getX());
                Blok.setLayoutY(event.getY());
            }
        });

        main_gui.canvas.getChildren().addAll(Blok);


        if(type.equals("Start")) {

            Start_Point point = new Start_Point(ret[0], Integer.parseInt(ret[1]), Blok, ciara);

        } else if(type.equals("End")) {

            End_Point point = new End_Point(ret[0], Integer.parseInt(ret[1]), Blok, ciara);

        }


        main_gui.makeBranch(ret[0], main_gui.leftMenu_items.get("Points"));

    }

    public static void add_add() {
        String name = add_block.display();
        if (name.isEmpty()) {
            return;
        }

        Group Blok = new Group();

        Rectangle rect = new Rectangle(60,60);
        rect.setFill(Color.BLUE);
        Label AddName = new Label(name);
        AddName.setLayoutX(25);
        AddName.setLayoutY(25);
        AddName.setTextFill(Color.BLACK);

        Blok.getChildren().addAll(rect, AddName);

        Blok.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Blok.setLayoutX(event.getX());
                Blok.setLayoutY(event.getY());
            }
        });

        main_gui.canvas.getChildren().addAll(Blok);


        new ADD_Block(name, Blok);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_sub() {
        String name = add_block.display();
        if (name.isEmpty()) {
            return;
        }

        Group Blok = new Group();

        Rectangle rect = new Rectangle(60,60);
        rect.setFill(Color.BLUE);
        Label AddName = new Label(name);
        AddName.setLayoutX(25);
        AddName.setLayoutY(25);
        AddName.setTextFill(Color.BLACK);

        Blok.getChildren().addAll(rect, AddName);


        Blok.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Blok.setLayoutX(event.getX());
                Blok.setLayoutY(event.getY());
            }
        });

        main_gui.canvas.getChildren().addAll(Blok);


        new SUB_Block(name, Blok);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_mul() {
        String name = add_block.display();
        if (name.isEmpty()) {
            return;
        }

        Group Blok = new Group();

        Rectangle rect = new Rectangle(60,60);
        rect.setFill(Color.BLUE);
        Label AddName = new Label(name);
        AddName.setLayoutX(25);
        AddName.setLayoutY(25);
        AddName.setTextFill(Color.BLACK);

        Blok.getChildren().addAll(rect, AddName);

        Blok.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Blok.setLayoutX(event.getX());
                Blok.setLayoutY(event.getY());
            }
        });


        main_gui.canvas.getChildren().addAll(Blok);


        new MUL_Block(name, Blok);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_div() {
        String name = add_block.display();
        if (name == null || name.isEmpty()) {
            return;
        }

        Group Blok = new Group();

        Rectangle rect = new Rectangle(60,60);
        rect.setFill(Color.BLUE);
        Label AddName = new Label(name);
        AddName.setLayoutX(25);
        AddName.setLayoutY(25);
        AddName.setTextFill(Color.BLACK);

        Blok.getChildren().addAll(rect, AddName);

        Blok.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Blok.setLayoutX(event.getX());
                Blok.setLayoutY(event.getY());
            }
        });


        main_gui.canvas.getChildren().addAll(Blok);


        new DIV_Block(name, Blok);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Blocks"));
    }

    public static void add_connection() {

        // Connection values name are random

        String ret[];
        ret = add_connection.display(); // return name, input, output

        if (ret[0] == null || ret[1] == null || ret[2] == null || ret[0].isEmpty() || ret[1].isEmpty() || ret[2].isEmpty()) {
            return;
        }

        String name = ret[0];

        Block input_block = Block.Blocks.get(ret[1]);
        Block output_block = Block.Blocks.get(ret[2]);

        OUT_Port output = output_block.AddOutput(UUID.randomUUID().toString());
        IN_Port input = input_block.AddInput(UUID.randomUUID().toString());

        Group input_group = input_block.getCanvasPoss();
        Group output_group = output_block.getCanvasPoss();

        double ix,iy,ox,oy;
        ix = input_group.getLayoutX();
        iy = input_group.getLayoutY() + 20;

        ox = output_group.getLayoutX() + 60;
        oy = output_group.getLayoutY() + 30;

        Line line = new Line(ix, iy, ox, oy);
        main_gui.canvas.getChildren().add(line);

        new Connection(name, output, input, line);

        main_gui.makeBranch(name, main_gui.leftMenu_items.get("Connections"));

    }

    public static void delete_point() {

    }

    public static void delete_block() {

        Block select = selectors.block();

        // Using stack because of ConcurrentModificationException

        Stack<IN_Port> IN = new Stack<>();
        Stack<OUT_Port> OUT = new Stack<>();

        if (!(select.PortIN.isEmpty())) {
            for (IN_Port port : select.PortIN) {
                IN.push(port);
            }
        }
        if (!(select.PortOUT.isEmpty())) {
            for (OUT_Port port : select.PortOUT) {
                OUT.push(port);
            }
        }

        while(!(IN.empty()))
            IN.pop().remove();

        while(!(OUT.empty()))
            OUT.pop().remove();

        Block.Blocks.remove(select.getName());

        main_gui.removeBranch(select.getName(), "Blocks");
        main_gui.canvas.getChildren().remove(select.CanvasPoss);

    }

    public static void delete_connection() {

        Connection select = selectors.connection();

        String name = select.getName();

        // Musi nastavit connections v blokoch na null
        select.getIn_port().setConnection(null);
        select.getOut_port().setConnection(null);

        // Vymazat z in bloku
        select.getIn_port().getBlock().RemoveInput(name);

        // Vymazat z out bloku
        select.getOut_port().getBlock().RemoveOutput(name);

        // Vymazat zo zoznamu
        Connection.Connections.remove(name);

        // GUI
        main_gui.removeBranch(name, "Connections");

        main_gui.canvas.getChildren().remove(select.getCiara());

    }
}

