import Car.Customer;

public class TestingQueryBase {
    public static CreateSeedDataSQLFile temp = CreateSeedDataSQLFile.getInstance();
    public static void main(String[] args) {
        temp.open();
        queryCustomer("mike@gmail.com");
        temp.close();
    }

    // testing customer query
    public static void queryCustomer(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Incorrect data ");
        } else {
            Customer customer = temp.queryCustomerTable(email);
            System.out.println(customer);
        }

    }
}
