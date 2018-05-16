package ija.Port;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Map;

/**
 * Connections between ports
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class Connection {

    private String name;

    private OUT_Port out_port;  //output predosleho bloku
    private IN_Port in_port;    //input do dalsieho bloku

    public boolean gate;
    public Group canvas;

    public static ArrayList<Connection> sequence;

    public static Map<String, Connection> Connections;

    /**
     * Constructor
     * @param name Name of connection
     * @param output Reference to output port
     * @param input Reference to input port
     */
    public Connection(String name, OUT_Port output, IN_Port input) {

        this.out_port = output;
        this.in_port = input;
        this.name = name;

        this.gate = false;

        output.setConnection(this);
        input.setConnection(this);

        Connections.put(name, this);

        sequence.add(this);

    }

    /**
     * Setter
     * @param output Reference to output port
     */
    public void setOut_port(OUT_Port output) {
        this.out_port = output;
    }

    /**
     * Getter
     * @return Reference to output port
     */
    public OUT_Port getOut_port() {
        return out_port;
    }

    /**
     * Setter
     * @param input Reference to input port
     */
    public void setIn_port(IN_Port input) {
        this.in_port = input;
    }

    /**
     * Getter
     * @return Reference to input port
     */
    public IN_Port getIn_port() {
        return in_port;
    }

    /**
     * Getter
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets equality between output and input port of connection
     */
    public void setEquation() {

        in_port.setValue(out_port.getValue());

        if(in_port.getBlock() != null) {    // For output of Point block

            in_port.getBlock().connections_set.put(in_port.getName(), true);

            boolean to_result = true;

            for (IN_Port port : in_port.getBlock().PortIN) {

                String name = port.getName();
                if (!in_port.getBlock().connections_set.get(name)) {
                    to_result = false;
                    break;
                }
            }

            if (to_result)
                in_port.getBlock().result();
        }
    }

}
