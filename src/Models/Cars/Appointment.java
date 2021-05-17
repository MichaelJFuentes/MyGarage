package Models.Cars;

import Models.Garage.Garage;

import java.sql.Timestamp;

public class Appointment {
    private Timestamp timestamp;
    private Car car;
    private Garage garage;

    public Appointment(Timestamp timestamp, Car car, Garage garage) {
        this.timestamp = timestamp;
        this.car = car;
        this.garage = garage;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
