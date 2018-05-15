package ija.Port;

import ija.Block.*;

/**
 * Port of Block in program
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public abstract class Port {

    protected Block block;
    protected Point point;
    private String name;
    protected double value;
    protected Connection connection;

    /**
     * Constructor
     * @param name Name of port
     * @param block Reference to block
     */
    public Port(String name, Block block) {

        this.name = name;
        this.value = 0.0;
        this.block = block;
        this.connection = null;

    }

    /**
     * Getter
     * @return Block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Setter
     * @param block Block
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    /**
     * Getter
     * @return Name of Port
     */
    public String getName() { return name; }

    /**
     * Getter
     * @return Connection from or to port
     */
    public Connection getConnection() { return connection; }

    /**
     * Setter
     * @param connection Connection from or to port
     */
    public void setConnection(Connection connection) { this.connection = connection; }

    /**
     * Getter
     * @return Value of port
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter
     * @param value Value of port
     */
    public abstract void setValue(double value);

    /**
     * Destructor
     */
    public abstract void remove();

    /**
     * Change of equality of objects
     * @param obj Reference
     * @return True/False
     */
    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Port))
            return false;

        return ((Port) obj).getName().equals(this.name);

    }

    /**
     * Change of equality of objects
     * @return HashValue
     */
    @Override
    public int hashCode() {
        return (name.length());
    }
}
