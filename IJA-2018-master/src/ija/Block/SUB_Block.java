package ija.Block;

import ija.Port.Port;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;


public class SUB_Block extends Block {

    public SUB_Block(String name, Group g) {
        super(name, g);
    }

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
