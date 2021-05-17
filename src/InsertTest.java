

import Car.Appointment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertTest {
    public static final CreateSeedDataSQLFile temp = CreateSeedDataSQLFile.getInstance();
    @BeforeAll
    public static void setup() throws Exception {
        temp.open();
    }

//    @Test
//    public void insertIntoMake(){
//        assertThrows(SQLException.class, () -> {temp.insertIntoMakeTable("Tesla");});
//    }
//
//    @ParameterizedTest
//    @ValueSource (strings = {"Ford", "Toyota", "Honda"})
//    public void insertIntoMake(String value) {
//        for (int i = 2; i < 2+3; i++) {
//            try {
//                int rValue = temp.insertIntoMakeTable(value);
//                assertEquals(i,rValue);
//            } catch (SQLException e ) {
//                e.printStackTrace();
//            }
//        }
//    }
    @ParameterizedTest
    @CsvSource({
            "Tesla, 1",
            "Ford, 2",
            "Toyota, 3",
            "Honda, 4"
    })
    void insertMakes(String name, int expectedId) {
        try {
            int rId = temp.insertIntoMakeTable(name);
            assertEquals(expectedId, rId);
        } catch (SQLException e ){

        }
    }

    @ParameterizedTest
    @CsvSource({
            "4runner, 3, 1",
            "civic, 4, 2",
            "accord, 4, 3",
            "model 3, 1, 4",
            "f 150, 2, 5",
            "corolla, 3, 6"
    })
    void insertModels(String name, int make_id, int expectedId) {
        try {
            int idReturned = temp.insertIntoModelTable(name, make_id);
            assertEquals(expectedId, idReturned);
        } catch (SQLException e ){
            e.printStackTrace();
        }

    }

    @ParameterizedTest
    @CsvSource({
            "Tesla, 1",
            "Ford, 2",
            "Toyota, 3",
            "Honda, 4"
    })
    void queryMakesTable(String make, int id) {
        int returnValue = temp.queryMakeTable(make).getId();
        assertEquals(id, returnValue);
    }

    @ParameterizedTest
    @CsvSource({
            "Gas monkey, gas monkey drive, 1",
            "Mikes garage, 123 main, 2",
            "shepered garage, 1 pirate drive, 3",
            "bob's place, road street, 4"
    })
    void insertGarage(String name, String location, int expectedId) {
        try {
            int returnValue = temp.insertIntoGarage(name, location);
            assertEquals(returnValue, expectedId);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "password, mike@gmail.com, 23423424, 23463, 1",
            "password, john@gmail.com, 535323424, 34634, 2",
            "youtube, smith@hotmail.com, 342523, 243245, 3",
            "makeSomething, graham@gmail.com, 45323234, 42372, 4"
    })
    void insertCustomers(String pass, String email, String phone, String zip, int expected) {
        try {
            int returnValue = temp.insertIntoCustomer(pass, email, phone, zip);
            assertEquals(expected, returnValue);
        } catch (SQLException e ){
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "password, mike@gmail.com, 23423424, 23463, 1",
            "password, john@gmail.com, 535323424, 34634, 2",
            "youtube, smith@hotmail.com, 342523, 243245, 3",
            "makeSomething, graham@gmail.com, 45323234, 42372, 4"
    })
    void queryCustomers(String pass, String email, String phone, String zip, int expected) {
        int returnValue = temp.queryCustomerTable(email ).getId();
        assertEquals(expected, returnValue);
    }

    @ParameterizedTest
    @CsvSource({
            "mech1@garage.com, 3242, 53452342, 2, 1, 1",
            "mech2@garage.com, 245423, 3554351, 2, 2, 2",
            "admin@garage.com, 23424, 23534523, 1, 1, 3"
    })
    void insertEmployee(String email, String pass, String phone, int employeeType, int garageId, int expected) {
        try {
            int returnValue = temp.insertEmployee(email, pass, phone, employeeType, garageId);
            assertEquals(expected, returnValue);
        } catch (SQLException e ){
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "vinOne, 1999, 250000, 1, 1, 1",
            "vinTwo, 2017, 15003, 4, 1, 2",
            "vinThree, 2020, 2030, 6, 1, 3"
    })
    void insertCars(String vin, int year, int miles, int model_id, int ownerId, int expected) {
        try {
            int returnValue = temp.insertCar(vin,year,miles,model_id,ownerId);
            assertEquals(expected, returnValue);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "mike@gmail.com, vinOne, 2021-01-10 10:14:25, Toyota, 4runner, 1999, Mikes garage"
//    })
//    void queryAppointment(String email, String vin, String dateTime, String make,
//                          String model, int year, String garageName) {
//
//            Appointment expectedAppointment =
//                    new Appointment(Timestamp.valueOf(dateTime), vin, make, model, year, garageName);
//            List<Appointment> appointments = temp.queryCustomerAppointments(email);
//            for (Appointment appointment : appointments) {
//                System.out.println(appointment);
//            }
//            assertEquals(vin, appointments.get(0).getVin());
//            Appointment actual = temp.queryCustomerAppointments(email);
//            assertEquals(vin, appointments.get(1).getVin());
//            assertEquals(dateTime, appointments.get(1).getTimestamp().toString());
//            assertEquals(make, appointments.get(1).getMake());
//            assertEquals(model, appointments.get(1).getModel());
//            assertEquals(year, appointments.get(1).getYear());
//            assertEquals(garageName, appointments.get(1).getGarage());
//            assertEquals(expectedAppointment, actual);
//
//    }

//    @ParameterizedTest
//    @CsvSource({"mike@gmail.com, vinOne"})
//    void queryCustomerAppointments(String email, String expectedVin) {
//        List<Appointment> appointments = temp.queryCustomerAppointments(email);
//        assertEquals(expectedVin, appointments.get(0).getVin());
//    }
//    @ParameterizedTest
//    @CsvSource({
//            "Note One Writing some random thing, 1, 1, 1",
//            "Today we're reacting to CNBC Make It - Living On $175K A Year In NYC & Georgia | Millennial Money , 2, 2, 2"
//    })
//    void insertNote(String note, int carId, int mechId, int expected) {
//        try {
//            int returnValue = temp.insertNote(note, carId, mechId);
//            assertEquals(expected, returnValue);
//        } catch (SQLException e ) {
//            e.printStackTrace();
//        }
//    }
// false mean no result or updated count
    @ParameterizedTest
    @CsvSource({
            "myMake, false",
            "Ya, false"
    })
    void deleteMake(String makeName, boolean expected) {
        try {
//             inserting make for testing
            temp.insertIntoMakeTable(makeName);
            assertEquals(expected,temp.deleteMake(makeName));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    //delete model test
    @ParameterizedTest
    @CsvSource({
        "MyModel, false"
    })
    void deleteModel(String modelName, boolean expectedResult) {
        try {
            int id = temp.insertIntoModelTable(modelName,1);
            boolean actual = temp.deleteModel(modelName);
            assertEquals(expectedResult, actual);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    // delete customer
    @ParameterizedTest
    @CsvSource(
            {
                    "graham@gmail.com, false"
            }
    )
    void deleteCustomer(String customerEmail, boolean expected) {
        try {
            assertEquals(expected,temp.deleteCustomer(customerEmail));

        } catch (SQLException e ){
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "vinThree, false"
    })
    void deleteCar(String vin, boolean expected) {
        try {
            assertEquals(expected, temp.deleteCar(vin));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({
        "bob's place, false"
    })
    void deleteGarage(String garageName, boolean expected) {
        try {
            assertEquals(expected, temp.deleteGarage(garageName));

        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }






    @AfterAll
    public static void close() throws Exception {
        temp.close();
    }
}
