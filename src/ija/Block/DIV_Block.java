package ija.Block;

import ija.Port.Port;

public class DIV_Block extends Block {

    public DIV_Block(String name) {
        super(name);
    }

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
