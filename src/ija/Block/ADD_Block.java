package ija.Block;

import ija.Port.Port;

public class ADD_Block extends Block {

    public ADD_Block(String name) {
        super(name);
    }

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
