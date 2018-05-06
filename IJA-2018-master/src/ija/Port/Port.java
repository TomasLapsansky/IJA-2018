package ija.Port;

import ija.Block.*;

public abstract class Port {

    protected Block block;
    protected Point point;
    private String name;
    protected double value;
    protected Connection connection;

    public Port(String name, Block block) {

        this.name = name;
        this.value = 0.0;
        this.block = block;
        this.connection = null;

    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getName() { return name; }

    public Connection getConnection() { return connection; }

    public void setConnection(Connection connection) { this.connection = connection; }

    public double getValue() {
        return value;
    }

    public abstract void setValue(double value);

    public abstract void remove();

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Port))
            return false;

        return ((Port) obj).getName().equals(this.name);

    }

    @Override
    public int hashCode() {
        return (name.length());
    }
}
