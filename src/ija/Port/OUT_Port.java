package ija.Port;

public class OUT_Port extends Port {

    private Connection con;

    public OUT_Port(String name) {

        super(name);
        con = null;

    }

    @Override
    public void setValue(double value) {

        this.value = value;

        if(con != null)     //if connection exists
            this.con.setEquation();

    }

    public void setConnection(Connection obj) {

        this.con = obj;

    }
}
