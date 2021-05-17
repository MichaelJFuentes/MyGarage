package Car;

public class Car {
    private int id;
    private String vin;
    private int year;
    private int miles;
    private String make;
    private String model;
    private String customer_email;

    public Car() {

    }

    public Car(int id, String vin, int year, int miles, String make, String model, String customer_email) {
        this.id = id;
        this.vin = vin;
        this.year = year;
        this.miles = miles;
        this.make = make;
        this.model = model;
        this.customer_email = customer_email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
// todo replace with custom one
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", year=" + year +
                ", miles=" + miles +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", customer_email='" + customer_email + '\'' +
                '}';
    }

}
