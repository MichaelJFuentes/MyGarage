package Car;

import java.sql.Timestamp;

public class Customer {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String zip;
    private Timestamp timestamp;

    public Customer() {

    }


    public Customer(int id, String username, String password, String email, String phone, String zip, Timestamp timestamp) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Id: ");
        builder.append(this.id);
        builder.append(String.format("%n%s: %s%n","Username",this.username));
        builder.append(String.format("%s: %s%n", "Password", this.password));
        builder.append(String.format("%s: %s%n", "Email", this.email));
        builder.append(String.format("%s: %s%n", "Phone", this.phone));
        builder.append(String.format("%s: %s%n", "Zip", this.zip));
        builder.append(String.format("%s: %s", "Created at", this.timestamp.toString()));
        return builder.toString();
    }
}
