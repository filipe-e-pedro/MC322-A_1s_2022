package connectricity;

public class Exit extends Entity{
    
    private boolean open = false;

    public String getName() {
        return "E";
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }
}
