package ija.Port;

import ija.Block.*;

public class IN_Port extends Port{

    public IN_Port(String name, Block block) {

        super(name, block);

    }

    public IN_Port(String name) {

        super(name, null);

    }

    @Override
    public void setValue(double value) {

        this.value = value;

    }

    @Override
    public void remove() {
        block.RemoveInput(this.getName());
    }
}
