package Car;

public class Model {
    private int id ;
    private String model;
    private String make;

    public Model() {

    }

    public Model(int id, String model, String make) {
        this.id = id;
        this.model = model;
        this.make = make;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Id: ");
        builder.append(this.id) ;
        builder.append("\n");
        builder.append("Make: " );
        builder.append(this.make);
        builder.append("\n");
        builder.append("Model: " );
        builder.append(this.model);
        return builder.toString();
    }
}
