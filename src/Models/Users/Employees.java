package Models.Users;

import Models.Garage.Garage;

public abstract class Employees extends User{
    private Models.Garage.Garage garage;
    private int employeeType;

    public Employees(String email, String password, String phone, Garage garage, int employeeType) {
        super(email, password, phone);
        this.garage = garage;
        this.employeeType = employeeType;
    }
}
