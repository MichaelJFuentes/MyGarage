package Car;

import java.sql.Timestamp;

public class Appointment {
    private Timestamp timestamp;
    private String vin;
    private String make;
    private String model;
    private int year;
    private String garage;

    public Appointment() {
    }

    public Appointment(Timestamp timestamp, String vin, String make, String model, int year, String garage) {
        this.timestamp = timestamp;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.garage = garage;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "timestamp=" + timestamp +
                ", vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", garage='" + garage + '\'' +
                '}';
    }
}
