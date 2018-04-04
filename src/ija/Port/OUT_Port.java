package ija.Port;

public class OUT_Port extends Port {

    private Connection con;

    public OUT_Port(String name, double value) {
        super(name, value);
        con = null;
    }

    @Override
    public void setValue(double value) {

        this.value = value;
        this.con.setEquation();

    }

    public void setConnection(Connection obj) {
        this.con = obj;
    }
}
