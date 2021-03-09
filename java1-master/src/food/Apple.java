package food;

public class Apple extends Food {
    private  String size;
    public Apple (String size)
    {
        super("Apple");
        this.size=size;
    }

    public void consume() {
        System.out.println(this + " was eaten");
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String toString() {
        return super.toString() + " size '" + size.toUpperCase() + "'";
    }
}
