package Models.Users;

public class Mechanic extends Employees {
    public Mechanic(String email, String password, String phone, Garage garage) {
        super(email, password, phone, garage, 2);
    }

}
