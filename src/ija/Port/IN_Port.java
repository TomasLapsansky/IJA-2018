package ija.Port;

import ija.Block.*;

/**
 * Input port of Block
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class IN_Port extends Port{

    /**
     * Constructor
     * @param name Name of port
     * @param block Reference of block
     */
    public IN_Port(String name, Block block) {

        super(name, block);

    }

    /**
     * Constructor without Block reference
     * @param name Name of port
     */
    public IN_Port(String name) {

        super(name, null);

    }

    /**
     * Setter
     * @param value Value of port
     */
    @Override
    public void setValue(double value) {

        this.value = value;

    }

    /**
     * Destructor
     */
    @Override
    public void remove() {
        block.RemoveInput(this.getName());
    }
}
