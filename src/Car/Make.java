package Car;

public class Make {
    private int id;
    private String name;

    public Make() {

    }
    public Make(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ID: ");
        builder.append(this.id);
        builder.append(String.format("%n%s: %s", "Make", this.name));
        return builder.toString();
    }

}
