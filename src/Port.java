import org.junit.*;
import org.hamcrest.*;

public class Port {

    private String name;
    private Double value;

    public Port(String name, Double value) {

        this.name = name;
        this.value = value;

    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Port))
            return false;

        return ((Port) obj).getName().equals(this.name);

    }

    @Override
    public int hashCode() {
        return (name.length());
    }
}
