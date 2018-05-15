package ija.Block;

import ija.Port.Port;

/**
 * Mul block
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class MUL_Block extends Block {

    /**
     * Constructor
     * @param name Name of Block
     */
    public MUL_Block(String name) {
        super(name);
    }

    /**
     * Implementation of multiplication
     */
    @Override
    public void result() {

        double result = 1;

        for (Port res: PortIN) {
            result *= res.getValue();
        }

        for (Port output: PortOUT) {
            output.setValue(result);
        }

    }
}
