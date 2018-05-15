package ija.Port;

import ija.Block.*;
import javafx.scene.control.Tooltip;

public class OUT_Port extends Port {

    public OUT_Port(String name, Block block) {

        super(name, block);

    }

    public OUT_Port(String name) {

        super(name, null);

    }

    @Override
    public void setValue(double value) {

        this.value = value;

        if(connection != null) {     // If connection exists
            this.connection.setEquation();

            // Actualize
            Tooltip.install(connection.canvas, new Tooltip(String.valueOf(this.getValue())));
        }

    }

    @Override
    public void remove() {
        block.RemoveOutput(this.getName());
    }
}
