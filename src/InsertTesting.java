import java.sql.SQLException;
import java.util.HashMap;

public class InsertTesting {
    public static CreateSeedDataSQLFile temp = CreateSeedDataSQLFile.getInstance();
    public static void main(String[] args) {
        temp.open();
        // code here
        // code to test inserting a new make

        // code to test inserting multiple makes

        // code to test inserting a single model

        // code to test inserting multiple makes and models(given the string of model name
        // code to test a new entry that works
//        insertGarage("Test Face Garage", "123 Main Drive");
//        // code to test a existing entry that should return a -1 values works
//        insertGarage("Test Face Garage", "123 Main Drive");
//        // testing multiple insert into garage, good values, works
//        HashMap<String, String> goodGarageValues = new HashMap<>();
//        goodGarageValues.put("Mike's Shop", "Dam good" );
//        goodGarageValues.put("Don't get it done", "Random road");
//        insertGarage(goodGarageValues);
//        // trying to add bad data that already exist, works
//        insertGarage(goodGarageValues);


        // code to add a new customer
//        insertCustomer("john788", "ismynametoo", "john443@gmail.com",
//                "4843187966", "45789");
        // code to test if adding a new customer that already exist fails

        // code to test adding multiple customer

        // code to test adding customer that already exist

        // code to add a single make
        try {
            temp.insertEmployee("MikeGarage@mikes.com", "helloWorld", "32425324", 1,1);
        } catch (Exception e ){
            e.printStackTrace();
        }
        temp.close();
    }
    // method to insert a single make
    public static void insertMake(String make) {
        try {
            int returnValue = temp.insertIntoMakeTable(make);
            System.out.println(returnValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // method to insert multiple makes

    // method to insert model

    // method to insert multiple models all it to

    // garage testing one insert, ,works inserting a new garage
    public static void insertGarage(String garageName, String garageLocation) {
        // no error checking in current method
        try {
            int returnIndex = temp.insertIntoGarage(garageName, garageLocation);
            System.out.println(returnIndex);
        } catch (SQLException e ) {
            System.out.println("Error updating database, nothing was changed");
            e.printStackTrace();
        }
    }
    // garage testing multiple insert works
    public static void insertGarage(HashMap<String, String> garages) {
        if (garages == null || garages.size() < 1) {
            // nothing to insert into the table
            return;
        } else {
            // loop over the current hash map to insert things
            // will skip over keys that already exist in the map
            try {
                for (String key : garages.keySet()) {
                    temp.insertIntoGarage(key, garages.get(key));
                    // not checking for -1 if the value exit in the system, it will return it's key
                    // otherwise it should return a sql exception
                }
            } catch (SQLException e ) {
                // should not be reached
                System.out.println("Error updating the database, in one or more items");
                e.printStackTrace();
            }
        }
    }

    // inserting a single value to customer
    public static void insertCustomer(String email, String password, String phone,
                                      String zip) {
        // guard clause
        if (isNullOrEmpty(password) || isNullOrEmpty(email) ||
                isNullOrEmpty(phone) || isNullOrEmpty(zip)) {
            // break out of current statement
            System.out.println("One or more of the values is missing, check the arguments ");
            return;
        } else {
            try {
                int value = temp.insertIntoCustomer(password, email, phone, zip);
                System.out.println(value);
            } catch (SQLException e ) {
                System.out.println("Error updating the table");
                e.printStackTrace();
            }
        }
    }

    // checking if null or empty
    public static boolean isNullOrEmpty(String value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return false;
    }
}
