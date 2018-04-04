import org.junit.*;
import org.hamcrest.*;

public class Connection {

    private Port out_input;
    private Port in_output;

    public Connection(Port input, Port output) {

        this.out_input = input;
        this.in_output = output;

    }

    public void setEquation() {
        out_input.setValue(in_output.getValue());
    }

}
