package ija.Port;

import java.util.Map;

public class Connection {

    private String name;

    private OUT_Port out_port;  //output predosleho bloku
    private IN_Port in_port;    //input do dalsieho bloku

    public boolean gate;

    public static Map<String, Connection> Connections;

    public Connection(String name, OUT_Port output, IN_Port input) {

        this.out_port = output;
        this.in_port = input;
        this.name = name;

        this.gate = false;

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
