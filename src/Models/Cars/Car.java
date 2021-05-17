package Models.Cars;

import Models.Users.Customer;

public class Car {
    private String vin;
    private String make;
    private String model;
    private int year;
    private int miles;
    private Customer customer;

    public Car(String vin, String make, String model, int year, int miles, Customer customer) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.miles = miles;
        this.customer = customer;
    }


}
