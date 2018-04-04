package ija.Port;

public class IN_Port extends Port{

    public IN_Port(String name, double value) {
        super(name, value);
    }

    @Override
    public void setValue(double value) {

        this.value = value;

    }
}
