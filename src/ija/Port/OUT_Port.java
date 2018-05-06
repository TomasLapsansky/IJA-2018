package ija.Port;

import ija.Block.*;

public class OUT_Port extends Port {

    public OUT_Port(String name, Block block) {

        super(name, block);

    }

    @Override
    public void setValue(double value) {

        this.value = value;

        if(connection != null)     //if connection exists
            this.connection.setEquation();

    }

    @Override
    public void remove() {
        block.RemoveOutput(this.getName());
    }
}
