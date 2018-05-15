package ija.Port;

import ija.Block.*;
import javafx.scene.control.Tooltip;

/**
 * Output port of Block
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class OUT_Port extends Port {

    /**
     * Constructor
     * @param name Name of port
     * @param block Reference of block
     */
    public OUT_Port(String name, Block block) {

        super(name, block);

    }

    /**
     * Constructor without block reference
     * @param name Name of port
     */
    public OUT_Port(String name) {

        super(name, null);

    }

    /**
     * Setter
     * @param value Value of port
     */
    @Override
    public void setValue(double value) {

        this.value = value;

        if(connection != null) {     // If connection exists
            this.connection.setEquation();

            // Actualize
            Tooltip.install(connection.canvas, new Tooltip(String.valueOf(this.getValue())));
        }

    }

    /**
     * Destructor
     */
    @Override
    public void remove() {
        block.RemoveOutput(this.getName());
    }
}
