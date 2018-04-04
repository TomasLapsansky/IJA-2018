package ija.Port;

public abstract class Port {

    private String name;
    protected double value;

    public Port(String name, double value) {

        this.name = name;
        this.value = value;

    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public abstract void setValue(double value);

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
