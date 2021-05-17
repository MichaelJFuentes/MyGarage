import Car.*;

import java.sql.SQLException;
import java.util.List;

public class RandomFirstTesting {
    public static final CreateSeedDataSQLFile temp = CreateSeedDataSQLFile.getInstance();
    public static void main(String[] args) {

        CreateSeedDataSQLFile.getInstance().open();
//        insertIntoMakeTableTest("GMC");
//        int makeId = queryMakeTableGetIdTest("ford");
//        printQueryMakeTableTest("Toyota");
//        printQueryModelTableTest("4runner");
//        printQueryCustomerTableTest("mike@gmail.com");
//        printQueryCarTableTest("VINONETEST");
//        printQueryGarageTableTest("testGarageName");
//        printQueryEmployeeTableTest("bob@gmail.com");
        // why is it case sensitive
//        List<Car> cars = queryCars("mike@gmail.com");
//        printCarsList(cars);
//        List<Appointment> appointments = temp.queryCarAppointments("Hello");
//        System.out.println(appointments.size());
//        temp.printQueryTest(temp.COLUMN_APPOINTMENT_DATE_TIME);
//        insertIntoModelTableTest("F-150", makeId);
//        int modelId = queryModelTableForIdTest("F-150");
//        System.out.println(temp.QUERY_CUSTOMERS_PREP);
//        System.out.println(modelId);
//        queryModelTableTest("F-150");
//        List<Appointment> appointments = temp.queryCustomerAppointments("mike@gmail.com");
//        System.out.println(appointments.size());
        List<Note> notes = temp.queryCarNotes("VINONETEST");
        for (Note i : notes) {
            System.out.println(i.toString());
        }

        CreateSeedDataSQLFile.getInstance().close();
    }
// works;
    public static void insertIntoMakeTableTest(String make) {
        try {
            CreateSeedDataSQLFile.getInstance().insertIntoMakeTable(make);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

// works;
    public static int queryMakeTableGetIdTest(String name) {
        return CreateSeedDataSQLFile.getInstance().queryMakeTableForId(name);
    }
// works;
//    public static boolean deleteFromMakeTableTest(String name) {
//        return temp.deleteFromMakeTable(name);
//    }

// works;
    public static void insertIntoModelTableTest(String name, int makeId) throws SQLException{
        temp.insertIntoModelTable(name, makeId);
    }

    public static void printQueryMakeTableTest(String name) {
        Make make = temp.queryMakeTable(name);
        System.out.println(make.toString());
    }
    public static void printQueryModelTableTest(String model) {
        Model model1 = temp.queryModelTable(model);
        System.out.println(model1.toString());
    }

    public static int queryModelTableForIdTest(String name) {
        return temp.queryModelTableForId(name);
    }

    public static void printQueryCustomerTableTest(String name) {
        Customer customer = temp.queryCustomerTable(name);
        System.out.println(customer.toString());
    }

    // print garages query
    public static void printQueryGarageTableTest(String name) {
        Garage garage = temp.queryGarageTable(name);
        System.out.println(garage);
    }

    // print employee query
    public static void printQueryEmployeeTableTest(String name) {
        Employee employee = temp.queryEmployeeTable(name);
        System.out.println(employee);
    }

    // print car query
    public static void printQueryCarTableTest(String vin) {
        Car car = temp.queryCarTable(vin);
        System.out.println(car.toString());
    }

    public static List<Car> queryCars(String email) {
        return temp.queryCustomerCars(email);
    }

    public static void printCarsList(List<Car> myList ) {
        if (myList != null && myList.size() > 0) {
            for (Car car : myList) {
                System.out.println(car.toString());
            }
        }
    }

    public static List<Appointment> queryCarAppointments(String vin) {
        return temp.queryCustomerAppointments(vin);
    }

    public static void printAppointmentsList(List<Appointment> appointments) {
        if (appointments != null && appointments.size() > 0) {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }




}
