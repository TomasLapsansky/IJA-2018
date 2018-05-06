package ija.Block;

import ija.Port.Port;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;


public class ADD_Block extends Block {

    public ADD_Block(String name, Group g) {
        super(name, g);
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
