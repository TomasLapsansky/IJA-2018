package ija.Block;

import ija.Port.Port;

public class MUL_Block extends Block {

    public MUL_Block(String name) {
        super(name);
    }

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
