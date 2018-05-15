package ija.Block;

import ija.Port.Port;

/**
 * Add block
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class ADD_Block extends Block {

    /**
     * Constructor
     * @param name Name of Block
     */
    public ADD_Block(String name) {
        super(name);
    }

    /**
     * Implementation of adding
     */
    @Override
    public void result() {

        double result = 0;

        for (Port res: PortIN) {
            result += res.getValue();
        }

        for (Port output: PortOUT) {
            output.setValue(result);
        }

    }
}
