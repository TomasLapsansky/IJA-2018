package ija.Block;

import ija.Port.Port;

/**
 * Sub block
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class SUB_Block extends Block {

    /**
     * Constructor
     * @param name Name of Block
     */
    public SUB_Block(String name) {
        super(name);
    }

    /**
     * Implementation of subtraction
     */
    @Override
    public void result() {

        double result = PortIN.get(0).getValue();

        for (int i = 1; i < PortIN.size(); i++) {

            result -= PortIN.get(i).getValue();

        }

        for (Port output: PortOUT) {
            output.setValue(result);
        }

    }

}
