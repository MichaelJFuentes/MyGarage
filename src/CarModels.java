import java.util.List;

public class CarModels {
    private String make;
    private List<String> models;

    public CarModels() {

    }

    public CarModels(String make, List<String> models) {
        this.make = make;
        this.models = models;
    }

    public String getMake() {
        return make;
    }

    public List<String> getModels() {
        return models;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }
}
