package ija.Port;

public class Connection {

    private OUT_Port out_port;  //output predosleho bloku
    private IN_Port in_port;    //input do dalsieho bloku

    /*
     * TODO
     * sposob vymazavanie connections a ich uprava
     * predbezny navrh: static pole so vsetkymi connections
     */

    public Connection(OUT_Port output, IN_Port input) {

        this.out_port = output;
        this.in_port = input;

        out_port.setConnection(this);

    }

    public void setEquation() {

        in_port.setValue(out_port.getValue());
    }

}
