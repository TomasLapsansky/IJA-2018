import java.util.*;
import org.junit.*;
import org.hamcrest.*;

abstract public class Block {

    private String name;
    private ArrayList<Port> PortIN;
    private ArrayList<Port> PortOUT;

    public Block(String name) {

        this.name = name;
        PortIN = new ArrayList<Port>();
        PortOUT = new ArrayList<Port>();

    }

    public String getName() {

        return this.name;

    }

    public void Rename(String name) {

        this.name = name;

    }

    public void AddInput(String name, Double value) {

        Port input = new Port(name, value);
        PortIN.add(input);

    }

    public void RemoveInput(String name) {

        Port remove = new Port(name, 0.0);
        PortIN.remove(remove);

    }

    public void AddOutput(String name, Double value) {

        Port output = new Port(name, value);
        PortOUT.add(output);

    }

    public void RemoveOutput(String name) {

        Port output = new Port(name, 0.0);
        PortOUT.remove(output);

    }

}
