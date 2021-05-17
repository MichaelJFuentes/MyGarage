package Models.Users;

public class Customer extends User{
    private String zip;

    public Customer(String email, String password, String phone, String zip) {
        super(email, password, phone);
        this.zip = zip;
    }


}
