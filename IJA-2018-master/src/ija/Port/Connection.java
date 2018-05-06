package ija.Port;

import javafx.scene.shape.Line;

import java.util.Map;

public class Connection {

    private String name;

    public Line ciara;
    private OUT_Port out_port;  //output predosleho bloku
    private IN_Port in_port;    //input do dalsieho bloku

    public static Map<String, Connection> Connections;

    public Connection(String name, OUT_Port output, IN_Port input, Line ciara) {

        this.ciara = ciara;
        this.out_port = output;
        this.in_port = input;
        this.name = name;

        output.setConnection(this);
        input.setConnection(this);

        Connections.put(name, this);

    }

    public void setOut_port(OUT_Port output) {
        this.out_port = output;
    }

    public OUT_Port getOut_port() {
        return out_port;
    }

    public void setIn_port(IN_Port input) {
        this.in_port = input;
    }

    public IN_Port getIn_port() {
        return in_port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquation() {

        in_port.setValue(out_port.getValue());
    }

    public Line getCiara(){

        return this.ciara;

    }

}
