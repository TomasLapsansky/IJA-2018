package ija.Block;

import ija.Port.Port;

/**
 * Div block
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class DIV_Block extends Block {

    /**
     * Constructor
     * @param name Name of Block
     */
    public DIV_Block(String name) {
        super(name);
    }

    /**
     * Implementation of division
     */
    @Override
    public void result() {

        double result = PortIN.get(0).getValue();

        for (int i = 1; i < PortIN.size(); i++) {

            result /= PortIN.get(i).getValue();

        }

        for (Port output: PortOUT) {
            output.setValue(result);
        }

    }
}
