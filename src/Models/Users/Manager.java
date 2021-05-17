package Models.Users;

import Models.Garage.Garage;

public class Manager extends Employees {
    public Manager(String email, String password, String phone, Garage garage) {
        super(email, password, phone, garage, 1);
    }


}
