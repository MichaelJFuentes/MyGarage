import Car.*;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateSeedDataSQLFile {
    public static final String DB_Name = "myGarage.db";
    public static final String CONNECTION_STRING =
            "jdbc:sqlite:C:\\Users\\micha\\Documents\\sqlite-tools-win32-x86-3330000\\" + DB_Name;
    public static final String SQL_FILE = "C:\\Users\\micha\\IdeaProjects\\MyGarage\\src\\createDatabase.sql";

    private Connection connection;

    public static final String TABLE_MAKES = "makes";
    public static final String COLUMN_MAKE_ID = "id";
    public static final String COLUMN_MAKE_NAME = "name";

    public static final String TABLE_MODELS = "models";
    public static final String COLUMN_MODEL_ID = "id";
    public static final String COLUMN_MODEL_NAME = "name";
    public static final String COLUMN_MODEL_MAKE_ID = "make_id";

    public static final String TABLE_CUSTOMERS = "customers";
    public static final String COLUMN_CUSTOMER_ID = "id";
//    public static final String COLUMN_CUSTOMER_USERNAME = "username";
    public static final String COLUMN_CUSTOMER_PASSWORD = "password";
    public static final String COLUMN_CUSTOMER_EMAIL = "email";
    public static final String COLUMN_CUSTOMER_PHONE = "phone";
    public static final String COLUMN_CUSTOMER_ZIP = "zip";
    public static final String COLUMN_CUSTOMER_CREATED_AT = "created_at";

    public static final String TABLE_CARS = "cars";
    public static final String COLUMN_CAR_ID = "id";
    public static final String COLUMN_CAR_VIN = "vin";
    public static final String COLUMN_CAR_YEAR = "year";
    public static final String COLUMN_CAR_MILES = "miles";
    public static final String COLUMN_CAR_MODEL_ID = "model_id";
    public static final String COLUMN_CAR_CUSTOMER_ID = "customer_id";

    public static final String TABLE_EMPLOYEE_TYPE = "employeeTypes";
    public static final String COLUMN_EMPLOYEE_TYPE_ID = "id";
    public static final String COLUMN_EMPLOYEE_TYPE_NAME = "name";

    public static final String TABLE_GARAGE = "garages";
    public static final String COLUMN_GARAGE_ID = "id";
    public static final String COLUMN_GARAGE_NAME = "name";
    public static final String COLUMN_GARAGE_LOCATION = "location";

    public static final String TABLE_EMPLOYEES = "employees";
    public static final String COLUMN_EMPLOYEE_ID = "id";
//    public static final String COLUMN_EMPLOYEE_USERNAME = "username";
    public static final String COLUMN_EMPLOYEE_PASSWORD = "password";
    public static final String COLUMN_EMPLOYEE_EMAIL = "email";
    public static final String COLUMN_EMPLOYEE_PHONE = "phone";
    public static final String COLUMN_EMPLOYEE_GARAGE_ID = "garage_id";
    public static final String COLUMN_EMPLOYEE_EMPLOYEE_TYPE_ID = "employeeType_id";

    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String COLUMN_APPOINTMENT_ID = "id";
    public static final String COLUMN_APPOINTMENT_DATE_TIME = "date_time";
    public static final String COLUMN_APPOINTMENT_CAR_ID = "car_id";
    public static final String COLUMN_APPOINTMENT_GARAGE_ID = "garage_id";

    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_NOTE_ID = "id";
    public static final String COLUMN_NOTE_NOTE = "note";
    public static final String COLUMN_NOTE_CAR_ID = "car_id";
    public static final String COLUMN_NOTE_MECHANIC_ID = "mechanic_id";
    public static final String COLUMN_NOTE_DATE_TIME = "date_time";


    // prepared statements for adding things
    // make
    public static final String ADD_TO_MAKE_PREP = "INSERT INTO " + TABLE_MAKES + "(" + COLUMN_MAKE_NAME + ") VALUES(?)";
    public static final String QUERY_MAKE_TABLE_FOR_ID_PREP = "SELECT " + COLUMN_MAKE_ID + " FROM " + TABLE_MAKES +
            " WHERE " + COLUMN_MAKE_NAME + " LIKE ?";
    public static final String QUERY_MAKE_TABLE_PREP = "SELECT " + COLUMN_MAKE_ID + ", " + COLUMN_MAKE_NAME +
            " FROM " + TABLE_MAKES + " WHERE " + COLUMN_MAKE_NAME + " = ?" ;


    public static final String ADD_TO_MODEL_PREP = "INSERT INTO " + TABLE_MODELS + " (" + COLUMN_MODEL_NAME + ", " +
            COLUMN_MODEL_MAKE_ID + ") VALUES(?, ?)";

    public static final String QUERY_MODEL_TABLE_FOR_ID_PREP = "SELECT " + COLUMN_MODEL_ID + " FROM " +
            TABLE_MODELS + " WHERE " + COLUMN_MODEL_NAME + " LIKE ?";
    public static final String QUERY_MODEL_TABLE_PREP = "SELECT " + TABLE_MODELS + "." + COLUMN_MODEL_ID + ", " +
                TABLE_MODELS + "." + COLUMN_MODEL_NAME + ", " + TABLE_MAKES + "." + COLUMN_MAKE_NAME +
                " FROM " + TABLE_MODELS +
            " INNER JOIN " + TABLE_MAKES + " ON " + TABLE_MAKES + "." + COLUMN_MAKE_ID + " = " +
                TABLE_MODELS + "." + COLUMN_MODEL_MAKE_ID +
            " WHERE "  + TABLE_MODELS + "." + COLUMN_MODEL_NAME + " LIKE ?";


    public static final String INSERT_INTO_CUSTOMERS_PREP = "INSERT INTO " + TABLE_CUSTOMERS + "(" +
            COLUMN_CUSTOMER_PASSWORD + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
            COLUMN_CUSTOMER_PHONE + ", " + COLUMN_CUSTOMER_ZIP + ") VALUES(?,?,?,?)";

    public static final String INSERT_INTO_GARAGES_PREP = "INSERT INTO " + TABLE_GARAGE + "(" + COLUMN_GARAGE_NAME +
            ", " + COLUMN_GARAGE_LOCATION + ") VALUES(?,?)";

    public static final String INSERT_INTO_EMPLOYEES = "INSERT INTO " + TABLE_EMPLOYEES + "(" +
            COLUMN_EMPLOYEE_PASSWORD + ", " + COLUMN_EMPLOYEE_EMAIL + ", " +
            COLUMN_EMPLOYEE_PHONE + ", " + COLUMN_EMPLOYEE_EMPLOYEE_TYPE_ID + ", " + COLUMN_EMPLOYEE_GARAGE_ID +
            ") VALUES(?,?,?,?,?)";

    public static final String INSERT_INTO_CARS_PREP = "INSERT INTO " + TABLE_CARS + "(" + COLUMN_CAR_VIN + ", " +
            COLUMN_CAR_MODEL_ID + ", " + COLUMN_CAR_YEAR + ", " + COLUMN_CAR_MILES + ", " + COLUMN_CAR_CUSTOMER_ID +
            ") VALUES(?,?,?,?,?)";

    public static final String INSERT_INTO_APPOINTMENTS_PREP = "INSERT INTO " + TABLE_APPOINTMENTS + "(" +
            COLUMN_APPOINTMENT_CAR_ID + ", " + COLUMN_APPOINTMENT_GARAGE_ID + ", " + COLUMN_APPOINTMENT_DATE_TIME +
            ") " + "VALUES(?,?,?)";
    // insert into table with current time
    public static final String INSERT_INTO_NOTES_PREP = "INSERT INTO " + TABLE_NOTES + "(" + COLUMN_NOTE_NOTE + ", " +
            COLUMN_NOTE_CAR_ID + ", " + COLUMN_NOTE_MECHANIC_ID + ") VALUES(?,?,?)";

    // query statements
    public static final String QUERY_CUSTOMERS_PREP = "SELECT " + COLUMN_CUSTOMER_ID  +
            ", " + COLUMN_CUSTOMER_PASSWORD + ", " + COLUMN_CUSTOMER_EMAIL + ", " + COLUMN_CUSTOMER_PHONE + ", " +
            COLUMN_CUSTOMER_ZIP + ", " + COLUMN_CUSTOMER_CREATED_AT + " FROM " + TABLE_CUSTOMERS +
            " WHERE " + COLUMN_CUSTOMER_EMAIL + " = ?";

    public static final String QUERY_GARAGES_PREP = "SELECT " + COLUMN_GARAGE_ID + ", " + COLUMN_GARAGE_NAME + ", " +
            COLUMN_GARAGE_LOCATION + " FROM " + TABLE_GARAGE + " WHERE " + COLUMN_GARAGE_NAME + " = ?";

    public static final String QUERY_EMPLOYEE_TYPE_PREP = "SELECT " + COLUMN_EMPLOYEE_TYPE_ID + ", " +
            COLUMN_EMPLOYEE_TYPE_NAME + " FROM " + TABLE_EMPLOYEE_TYPE + " WHERE " + COLUMN_EMPLOYEE_TYPE_NAME +
            " LIKE ?";
    public static final String QUERY_EMPLOYEE_PREP = "SELECT " + TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_ID + ", "
            + TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_PASSWORD + ", " +
            TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_EMAIL + ", " +
            TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_PHONE + ", " + TABLE_GARAGE + "." + COLUMN_GARAGE_NAME +
            ", " + TABLE_EMPLOYEE_TYPE + "." + COLUMN_EMPLOYEE_TYPE_NAME +
            " FROM " + TABLE_EMPLOYEES +
            " INNER JOIN " + TABLE_EMPLOYEE_TYPE + " ON " + TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_EMPLOYEE_TYPE_ID +
                " = " + TABLE_EMPLOYEE_TYPE + "." + COLUMN_EMPLOYEE_TYPE_ID +
            " INNER JOIN " + TABLE_GARAGE + " ON " + TABLE_GARAGE + "." + COLUMN_GARAGE_ID + " = " +
                TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_GARAGE_ID +
            " WHERE " + TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_EMAIL + " = ?";

    public static final String QUERY_CAR_VIA_VIN_PREP = "SELECT " + TABLE_CARS + "." + COLUMN_CAR_ID + ", " +
            TABLE_CARS + "." + COLUMN_CAR_VIN + ", " + TABLE_CARS + "." + COLUMN_CAR_YEAR + ", " +
            TABLE_CARS + "." + COLUMN_CAR_MILES + ", " + TABLE_MAKES + "." + COLUMN_MAKE_NAME + ", " +
            TABLE_MODELS + "." + COLUMN_MODEL_NAME + ", " + TABLE_CUSTOMERS + "." + COLUMN_CUSTOMER_EMAIL +
            " FROM " + TABLE_CARS +
            " INNER JOIN " + TABLE_MODELS + " ON " + TABLE_MODELS + "." + COLUMN_MODEL_ID + " = " +
                TABLE_CARS + "." + COLUMN_CAR_MODEL_ID +
            " INNER JOIN " + TABLE_MAKES + " ON " + TABLE_MAKES + "." + COLUMN_MAKE_ID + " = " +
                TABLE_MODELS + "." + COLUMN_MODEL_MAKE_ID +
            " INNER JOIN " + TABLE_CUSTOMERS + " ON " + TABLE_CUSTOMERS + "." + COLUMN_CUSTOMER_ID + " = " +
                TABLE_CARS + "." + COLUMN_CAR_CUSTOMER_ID +
            " WHERE " + TABLE_CARS + "." + COLUMN_CAR_VIN + " = ?";

    public static final String QUERY_CAR_VIA_EMAIL = "SELECT " + TABLE_CARS + "." + COLUMN_CAR_ID + ", " +
            TABLE_CARS + "." + COLUMN_CAR_VIN + ", " + TABLE_CARS + "." + COLUMN_CAR_YEAR + ", " +
            TABLE_CARS + "." + COLUMN_CAR_MILES + ", " + TABLE_MAKES + "." + COLUMN_MAKE_NAME + ", " +
            TABLE_MODELS + "." + COLUMN_MODEL_NAME + ", " + TABLE_CUSTOMERS + "." + COLUMN_CUSTOMER_EMAIL +
            " FROM " + TABLE_CARS +
            " INNER JOIN " + TABLE_MODELS + " ON " + TABLE_MODELS + "." + COLUMN_MODEL_ID + " = " +
            TABLE_CARS + "." + COLUMN_CAR_MODEL_ID +
            " INNER JOIN " + TABLE_MAKES + " ON " + TABLE_MAKES + "." + COLUMN_MAKE_ID + " = " +
            TABLE_MODELS + "." + COLUMN_MODEL_MAKE_ID +
            " INNER JOIN " + TABLE_CUSTOMERS + " ON " + TABLE_CUSTOMERS + "." + COLUMN_CUSTOMER_ID + " = " +
            TABLE_CARS + "." + COLUMN_CAR_CUSTOMER_ID +
            " WHERE " + TABLE_CUSTOMERS + "." + COLUMN_CUSTOMER_EMAIL + " = ?";

    public static final String QUERY_CAR_APPOINTMENTS = "SELECT " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE_TIME +
            ", " + TABLE_CARS + "." + COLUMN_CAR_VIN + ", " + TABLE_MAKES + "." + COLUMN_MAKE_NAME + ", " +
            TABLE_MODELS + "." + COLUMN_MODEL_NAME + ", " + TABLE_CARS + "." + COLUMN_CAR_YEAR + ", " +
            TABLE_GARAGE + "." + COLUMN_GARAGE_NAME +
            " FROM " + TABLE_APPOINTMENTS +
            " INNER JOIN " + TABLE_CARS + " ON " + TABLE_CARS + "." + COLUMN_CAR_ID + " = " +
                TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_CAR_ID +
            " INNER JOIN " + TABLE_MODELS + " ON " + TABLE_MODELS + "." + COLUMN_MODEL_ID + " = " +
                TABLE_CARS + "." + COLUMN_CAR_MODEL_ID +
            " INNER JOIN " + TABLE_MAKES + " ON " + TABLE_MAKES + "." + COLUMN_MAKE_ID + " = " +
                TABLE_MODELS + "." + COLUMN_MODEL_MAKE_ID +
            " INNER JOIN " + TABLE_GARAGE + " ON " + TABLE_GARAGE + "." + COLUMN_GARAGE_ID + " = " +
                TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_GARAGE_ID +
//            " WHERE " + TABLE_CARS + "." + COLUMN_CAR_VIN + " = ? " +
            " ORDER BY " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE_TIME + " DESC";

    public static final String QUERY_CAR_APPOINTMENT_SINGLE = "SELECT " + TABLE_APPOINTMENTS + "." +
            COLUMN_APPOINTMENT_ID + ", " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE_TIME +
            ", " + TABLE_CARS + "." + COLUMN_CAR_VIN + ", " + TABLE_MAKES + "." + COLUMN_MAKE_NAME + ", " +
            TABLE_MODELS + "." + COLUMN_MODEL_NAME + ", " + TABLE_CARS + "." + COLUMN_CAR_YEAR + ", " +
            TABLE_GARAGE + "." + COLUMN_GARAGE_NAME +
            " FROM " + TABLE_APPOINTMENTS +
            " INNER JOIN " + TABLE_CARS + " ON " + TABLE_CARS + "." + COLUMN_CAR_ID + " = " +
            TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_CAR_ID +
            " INNER JOIN " + TABLE_MODELS + " ON " + TABLE_MODELS + "." + COLUMN_MODEL_ID + " = " +
            TABLE_CARS + "." + COLUMN_CAR_MODEL_ID +
            " INNER JOIN " + TABLE_MAKES + " ON " + TABLE_MAKES + "." + COLUMN_MAKE_ID + " = " +
            TABLE_MODELS + "." + COLUMN_MODEL_MAKE_ID +
            " INNER JOIN " + TABLE_GARAGE + " ON " + TABLE_GARAGE + "." + COLUMN_GARAGE_ID + " = " +
            TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_GARAGE_ID +
            " WHERE " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE_TIME + " = ? AND " + TABLE_CARS + "." +
            COLUMN_CAR_VIN + " = ?";

    public static final String QUERY_CUSTOMER_APPOINTMENTS = "SELECT " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE_TIME +
            ", " + TABLE_CARS + "." + COLUMN_CAR_VIN + ", " + TABLE_MAKES + "." + COLUMN_MAKE_NAME + ", " +
            TABLE_MODELS + "." + COLUMN_MODEL_NAME + ", " + TABLE_CARS + "." + COLUMN_CAR_YEAR + ", " +
            TABLE_GARAGE + "." + COLUMN_GARAGE_NAME +
            " FROM " + TABLE_APPOINTMENTS +
            " INNER JOIN " + TABLE_CARS + " ON " + TABLE_CARS + "." + COLUMN_CAR_ID + " = " +
            TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_CAR_ID +
            " INNER JOIN " + TABLE_MODELS + " ON " + TABLE_MODELS + "." + COLUMN_MODEL_ID + " = " +
            TABLE_CARS + "." + COLUMN_CAR_MODEL_ID +
            " INNER JOIN " + TABLE_MAKES + " ON " + TABLE_MAKES + "." + COLUMN_MAKE_ID + " = " +
            TABLE_MODELS + "." + COLUMN_MODEL_MAKE_ID +
            " INNER JOIN " + TABLE_GARAGE + " ON " + TABLE_GARAGE + "." + COLUMN_GARAGE_ID + " = " +
            TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_GARAGE_ID +
            " INNER JOIN " + TABLE_CUSTOMERS + " ON " + TABLE_CUSTOMERS + "." + COLUMN_CUSTOMER_ID + " = " +
                TABLE_CARS + "." + COLUMN_CAR_CUSTOMER_ID +
            " WHERE " + TABLE_CUSTOMERS + "." + COLUMN_CUSTOMER_EMAIL + " = ?" +
            " ORDER BY " + TABLE_APPOINTMENTS + "." + COLUMN_APPOINTMENT_DATE_TIME + " DESC";

    public static final String QUERY_CAR_NOTE = "SELECT " + TABLE_NOTES + "." + COLUMN_NOTE_ID + "," +
            TABLE_NOTES + "." + COLUMN_NOTE_NOTE + ", " +
            TABLE_GARAGE + "." + COLUMN_GARAGE_NAME + ", " + TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_EMAIL + ", " +
            TABLE_NOTES + "." + COLUMN_NOTE_DATE_TIME +
            " FROM " + TABLE_NOTES +
            " INNER JOIN " + TABLE_EMPLOYEES + " ON " + TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_ID + " = " +
                TABLE_NOTES + "." + COLUMN_NOTE_MECHANIC_ID +
            " INNER JOIN " + TABLE_GARAGE + " ON " + TABLE_GARAGE + "." + COLUMN_GARAGE_ID + " = " +
                TABLE_EMPLOYEES + "." + COLUMN_EMPLOYEE_GARAGE_ID +
            " INNER JOIN " + TABLE_CARS + " ON " + TABLE_CARS + "." + COLUMN_CAR_ID + " = " +
                TABLE_NOTES + "." + COLUMN_NOTE_CAR_ID +
            " WHERE " + TABLE_CARS + "." + COLUMN_CAR_VIN + " = ? " +
            " ORDER BY " + TABLE_NOTES + "." + COLUMN_NOTE_DATE_TIME + " DESC";

    public static final String QUERY_LAST_NOTE_ID = "SELECT " + TABLE_NOTES + "." + COLUMN_NOTE_ID + " FROM " +
            TABLE_NOTES + " ORDER BY " + TABLE_NOTES + "." + COLUMN_NOTE_DATE_TIME + " DESC LIMIT 1";

    // prepared statements to delete things from rows
    public static final String DELETE_FROM_MAKE_TABLE_PREP = "DELETE FROM " + TABLE_MAKES + " WHERE " + COLUMN_MAKE_NAME +
            " = ?";
    public static final String DELETE_FROM_MODEL_TABLE_PREP = "DELETE FROM " + TABLE_MODELS + " WHERE " +
            COLUMN_MODEL_NAME + " = ?";
    public static final String DELETE_FROM_CAR_TABLE_PREP = "DELETE FROM " + TABLE_CARS + " WHERE " +
            COLUMN_CAR_VIN + " = ?";
    public static final String DELETE_FROM_CUSTOMER_TABLE_PREP = "DELETE FROM " + TABLE_CUSTOMERS + " WHERE " +
            COLUMN_CUSTOMER_EMAIL + " = ?";
    public static final String DELETE_FROM_GARAGE_TABLE_PREP = "DELETE FROM " + TABLE_GARAGE + " WHERE " +
            COLUMN_GARAGE_NAME + " = ?";
    public static final String DELETE_FROM_EMPLOYEE_TABLE_PREP = "DELETE FROM " + TABLE_EMPLOYEES + " WHERE " +
            COLUMN_EMPLOYEE_EMAIL + " = ?";
    public static final String DELETE_FROM_NOTES_PREP = "DELETE FROM " + TABLE_NOTES + " WHERE " +
            COLUMN_NOTE_ID + " = ?";


    public static final String QUERY_BASE_TABLE_PREP = "SELECT * FROM ?";

    //    prepared statements for adding things
    private PreparedStatement insertIntoMakes;
    private PreparedStatement queryMakeTableForId;
    private PreparedStatement queryMakeTable;
    private PreparedStatement deleteItemFromMakeTablePrepStat;

    private PreparedStatement insertIntoModels;
    private PreparedStatement queryModelTableForIdPrepStat;
    private PreparedStatement queryModelTablePrepStat;
    private PreparedStatement deleteModelPrepStat;


    private PreparedStatement insertIntoCustomersPrepStat;
    private PreparedStatement queryCustomersPrepStat;
    private PreparedStatement deleteCustomerPrepStat;

    private PreparedStatement insertIntoCarsPrepStat;
    private PreparedStatement queryCarsPrepStat;
    private PreparedStatement queryCarsViaEmailPrepStat;
    private PreparedStatement deleteCarPrepStat;

    private PreparedStatement insertIntoGaragesPrepStat;
    private PreparedStatement queryGaragesPrepStat;
    private PreparedStatement deleteGaragePrepStat;

    private PreparedStatement insertIntoEmployeesPrepStat;
    private PreparedStatement queryEmployeesPrepStat;
    private PreparedStatement deleteEmployeePrepStat;

    private PreparedStatement insertIntoAppointmentsPrepStat;
    private PreparedStatement queryCarAppointmentsPrepStat;
    private PreparedStatement queryCustomerAppointmentsPrepStat;
    private PreparedStatement querySingleAppointment;
    private PreparedStatement deleteAppointmentPrepStat;

    private PreparedStatement insertIntoNotesPrepStat;
    private PreparedStatement queryNotesPrepStat;
    private PreparedStatement deleteNotePrepStat;

    private PreparedStatement queryEmployeeTypePrepStat;

    private PreparedStatement queryBaseTable;

    private static CreateSeedDataSQLFile instance = new CreateSeedDataSQLFile();
    private CreateSeedDataSQLFile() {

    }

    public static CreateSeedDataSQLFile getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);

            insertIntoMakes = connection.prepareStatement(ADD_TO_MAKE_PREP, Statement.RETURN_GENERATED_KEYS);
            queryMakeTable = connection.prepareStatement(QUERY_MAKE_TABLE_PREP);
            queryMakeTableForId = connection.prepareStatement(QUERY_MAKE_TABLE_FOR_ID_PREP);
            deleteItemFromMakeTablePrepStat = connection.prepareStatement(DELETE_FROM_MAKE_TABLE_PREP);

            insertIntoModels = connection.prepareStatement(ADD_TO_MODEL_PREP, Statement.RETURN_GENERATED_KEYS);
            queryModelTableForIdPrepStat = connection.prepareStatement(QUERY_MODEL_TABLE_FOR_ID_PREP);
            queryModelTablePrepStat = connection.prepareStatement(QUERY_MODEL_TABLE_PREP);
            deleteModelPrepStat = connection.prepareStatement(DELETE_FROM_MODEL_TABLE_PREP);

            insertIntoCustomersPrepStat = connection.prepareStatement(INSERT_INTO_CUSTOMERS_PREP,
                    Statement.RETURN_GENERATED_KEYS);
            queryCustomersPrepStat = connection.prepareStatement(QUERY_CUSTOMERS_PREP);
            deleteCustomerPrepStat = connection.prepareStatement(DELETE_FROM_CUSTOMER_TABLE_PREP);

            insertIntoGaragesPrepStat = connection.prepareStatement(INSERT_INTO_GARAGES_PREP,
                    Statement.RETURN_GENERATED_KEYS);
            queryGaragesPrepStat = connection.prepareStatement(QUERY_GARAGES_PREP);
            deleteGaragePrepStat = connection.prepareStatement(DELETE_FROM_GARAGE_TABLE_PREP);

            insertIntoEmployeesPrepStat = connection.prepareStatement(INSERT_INTO_EMPLOYEES,
                    Statement.RETURN_GENERATED_KEYS);
            queryEmployeesPrepStat = connection.prepareStatement(QUERY_EMPLOYEE_PREP);

            insertIntoCarsPrepStat = connection.prepareStatement(INSERT_INTO_CARS_PREP,
                    Statement.RETURN_GENERATED_KEYS);
            queryCarsPrepStat = connection.prepareStatement(QUERY_CAR_VIA_VIN_PREP);
            queryCarsViaEmailPrepStat = connection.prepareStatement(QUERY_CAR_VIA_EMAIL);
            deleteCarPrepStat = connection.prepareStatement(DELETE_FROM_CAR_TABLE_PREP);

            insertIntoAppointmentsPrepStat = connection.prepareStatement(INSERT_INTO_APPOINTMENTS_PREP,
                    Statement.RETURN_GENERATED_KEYS);
            queryCarAppointmentsPrepStat = connection.prepareStatement(QUERY_CAR_APPOINTMENTS);
            queryCustomerAppointmentsPrepStat = connection.prepareStatement(QUERY_CUSTOMER_APPOINTMENTS);
            querySingleAppointment = connection.prepareStatement(QUERY_CAR_APPOINTMENT_SINGLE);

            insertIntoNotesPrepStat = connection.prepareStatement(INSERT_INTO_NOTES_PREP,
                    Statement.RETURN_GENERATED_KEYS);

            queryEmployeeTypePrepStat = connection.prepareStatement(QUERY_EMPLOYEE_TYPE_PREP);

            queryNotesPrepStat = connection.prepareStatement(QUERY_CAR_NOTE);
            deleteNotePrepStat = connection.prepareStatement(DELETE_FROM_NOTES_PREP );

//            queryBaseTable = connection.prepareStatement("SELECT ? FROM " + TABLE_APPOINTMENTS);
            return true;
        } catch (SQLException e ) {
            System.out.println("Error opening database");
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            // replace if statements with some type of loop
            if (insertIntoMakes != null) {
                insertIntoMakes.close();
            }
            if (insertIntoModels != null) {
                insertIntoModels.close();
            }
            if (insertIntoCustomersPrepStat != null) {
                insertIntoCustomersPrepStat.close();
            }
            if (insertIntoGaragesPrepStat != null) {
                insertIntoGaragesPrepStat.close();
            }
            if (insertIntoEmployeesPrepStat != null) {
                insertIntoEmployeesPrepStat.close();
            }
            if (insertIntoCarsPrepStat != null) {
                insertIntoCarsPrepStat.close();
            }
            if (insertIntoAppointmentsPrepStat != null) {
                insertIntoAppointmentsPrepStat.close();
            }
            if (insertIntoNotesPrepStat != null) {
                insertIntoNotesPrepStat.close();
            }

            // basic query section
            if (queryModelTablePrepStat != null ) {
                queryModelTablePrepStat.close();
            }
            if (queryEmployeeTypePrepStat != null) {
                queryEmployeeTypePrepStat.close();
            }
            if (queryMakeTableForId != null) {
                queryMakeTableForId.close();
            }
            if (queryMakeTable != null) {
                queryMakeTable.close();
            }
            if (queryCustomersPrepStat != null) {
                queryCustomersPrepStat.close();
            }
            if (queryCarsPrepStat != null) {
                queryCarsPrepStat.close();
            }
            if (queryCarsViaEmailPrepStat != null) {
                queryCarsViaEmailPrepStat.close();
            }
            if (queryGaragesPrepStat != null) {
                queryGaragesPrepStat.close();
            }
            if (queryEmployeesPrepStat != null) {
                queryEmployeesPrepStat.close();
            }
            if (queryCarAppointmentsPrepStat != null) {
                queryCarAppointmentsPrepStat.close();
            }
            if (queryCustomerAppointmentsPrepStat != null) {
                queryCustomerAppointmentsPrepStat.close();
            }
            if (queryNotesPrepStat != null) {
                queryNotesPrepStat.close();
            }
            if (querySingleAppointment != null) {
                querySingleAppointment.close();
            }


            // delete user section
            if (deleteItemFromMakeTablePrepStat != null) {
                deleteItemFromMakeTablePrepStat.close();
            }
            if (queryModelTableForIdPrepStat != null) {
                queryModelTableForIdPrepStat.close();
            }
            if (deleteModelPrepStat != null) {
                deleteModelPrepStat.close();
            }
            if (deleteCustomerPrepStat != null) {
                deleteCustomerPrepStat.close();
            }
            if (deleteCarPrepStat != null) {
                deleteCarPrepStat.close();
            }
            if (deleteGaragePrepStat != null) {
                deleteGaragePrepStat.close();
            }
            if (deleteEmployeePrepStat != null) {
                deleteEmployeePrepStat.close();
            }
            if (deleteAppointmentPrepStat != null) {
                deleteAppointmentPrepStat.close();
            }
            if (deleteNotePrepStat != null) {
                deleteNotePrepStat.close();
            }

            if (queryBaseTable != null) {
                queryBaseTable.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e ) {
            System.out.println("something occurred when closing down");
            e.printStackTrace();
        }

    }

    private CarModels parseLineForMakeAndModel(String line) {
        String patternString = "\\[\\{\"brand\": \"(.*?)\", \"models\": \\[(.*?)]}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);
        CarModels carModels = new CarModels();
        while (matcher.find()) {
            String brand = matcher.group(1);
            String[] models = matcher.group(2).split(",");
            carModels.setMake(brand);
            carModels.setModels(List.of(models));
        }
        return carModels;
    }


    public int insertIntoMakeTable(String make) throws SQLException{
        if (make == null) {
            return -1;
        }
//        int queryResult = queryMakeTableForId(make);
        queryMakeTableForId.setString(1, make);
        ResultSet result = queryMakeTable.executeQuery();
        if (result.next()){
            return result.getInt(1);
        } else {
            insertIntoMakes.setString(1, make);
            int affectedRows = insertIntoMakes.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Error inserting data into makes table");
            }

            ResultSet resultSet = insertIntoMakes.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Error inserting into make table");
            }
        }

    }

    public int queryMakeTableForId(String name) {
        if (!name.isEmpty()) {
            try {
                queryMakeTableForId.setString(1, name);
                ResultSet resultSet = queryMakeTableForId.executeQuery();
                return resultSet.getInt(1);
            } catch (SQLException e ) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public Make queryMakeTable(String name) {
        Make temp = new Make();
        ResultSet resultSet = null;
        if (!name.isEmpty()) {
            try {
                queryMakeTable.setString(1, name);
                resultSet = queryMakeTable.executeQuery();
                if (resultSet.next()) {
                    temp.setId(resultSet.getInt(1));
                    temp.setName(resultSet.getString(2));
                }
                queryMakeTable.clearParameters();
            } catch (SQLException e ){
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e ) {
                    e.printStackTrace();
                }

            }
        }
        return temp;

    }


    public int insertIntoModelTable(String model, int makeId) throws SQLException{
        if (model.isEmpty() || makeId < 1) {
            return -1;
        }

        queryModelTableForIdPrepStat.setString(1, model);
        ResultSet resultSet = queryModelTableForIdPrepStat.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            insertIntoModels.setString(1, model);
            insertIntoModels.setInt(2, makeId);
            int affectedRows = insertIntoModels.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Error inserting data");
            }

            ResultSet keys = insertIntoMakes.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            } else {
                throw new SQLException("Error inserting data");
            }
        }
    }

    // query id from model table
    public int queryModelTableForId(String modelName) {
        if (modelName != null ) {
            try {
                queryModelTableForIdPrepStat.setString(1, modelName);
                ResultSet resultSet = queryModelTableForIdPrepStat.executeQuery();
                return resultSet.getInt(1);
            } catch (SQLException e ){
                e.printStackTrace();
            }
        }
        return -1;
    }
    // query model from model table
    public Model queryModelTable(String modelName) {
        Model model = new Model();
        ResultSet resultSet = null;
        if (!modelName.isEmpty()) {
            try {
                queryModelTablePrepStat.setString(1, modelName);
                resultSet = queryModelTablePrepStat.executeQuery();
                if (resultSet.next()) {
                    model.setId(resultSet.getInt(1));
                    model.setModel(resultSet.getString(2));
                    model.setMake(resultSet.getString(3));
                }
            } catch (SQLException e ) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException e ) {
                    e.printStackTrace();
                }
            }
        }
        return model;
    }

    public Customer queryCustomerTable(String customerEmail) {
        Customer customer = new Customer();
        ResultSet resultSet = null;
        if (customerEmail != null) {
            try {
//                System.out.println(QUERY_CUSTOMERS_PREP);
                queryCustomersPrepStat.setString(1, customerEmail);
                resultSet = queryCustomersPrepStat.executeQuery();
                queryCustomersPrepStat.clearParameters();
                if (resultSet.next()) {
                    customer.setId(resultSet.getInt(1));
                    customer.setPassword(resultSet.getString(2));
                    customer.setEmail(resultSet.getString(3));
                    customer.setPhone(resultSet.getString(4));
                    customer.setZip(resultSet.getString(5));
                    customer.setTimestamp(Timestamp.valueOf(resultSet.getString(6)));
                }

            } catch (SQLException e ) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e ){
                    e.printStackTrace();
                }
            }
        }
        return customer;
    }

    public Garage queryGarageTable(String garageName) {
        Garage garage = new Garage();
        ResultSet resultSet = null;
        if (garageName != null) {
            try {
                queryGaragesPrepStat.setString(1, garageName);
                resultSet = queryGaragesPrepStat.executeQuery();
                queryGaragesPrepStat.clearParameters();
                if (resultSet.next()) {
                    garage.setId(resultSet.getInt(1));
                    garage.setName(resultSet.getString(2));
                    garage.setLocation(resultSet.getString(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return garage;
    }

    public Employee queryEmployeeTable(String email) {
        Employee employee = new Employee();
        ResultSet resultSet = null;
        if (email != null) {
            try {
                queryEmployeesPrepStat.setString(1, email);
                resultSet = queryEmployeesPrepStat.executeQuery();
                queryEmployeesPrepStat.clearParameters();
                if (resultSet.next()) {
                    employee.setId(resultSet.getInt(1));
                    employee.setPassword(resultSet.getString(2));
                    employee.setEmail(resultSet.getString(3));
                    employee.setPhone(resultSet.getString(4));
                    employee.setGarage(resultSet.getString(5));
                    employee.setEmployeeType(resultSet.getString(6));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e ) {
                    e.printStackTrace();
                }
            }
        }
        return employee;
    }

    public Car queryCarTable(String vin) {
        Car car = new Car();
        ResultSet resultSet = null;
        if (vin != null) {
            try {
                queryCarsPrepStat.setString(1, vin);
                resultSet = queryCarsPrepStat.executeQuery();
                queryCarsPrepStat.clearParameters();
                if (resultSet.next()) {
                    car.setId(resultSet.getInt(1));
                    car.setVin(resultSet.getString(2));
                    car.setYear(resultSet.getInt(3));
                    car.setMiles(resultSet.getInt(4));
                    car.setMake(resultSet.getString(5));
                    car.setModel(resultSet.getString(6));
                    car.setCustomer_email(resultSet.getString(7));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return car;
    }

    // query cars for user (multiple )
    public List<Car> queryCustomerCars(String email) {
        List<Car> cars = new ArrayList<Car>();
        if (email != null && !email.isEmpty()) {
            ResultSet resultSet = null;
            try {
                queryCarsViaEmailPrepStat.setString(1, email);
                resultSet = queryCarsViaEmailPrepStat.executeQuery();
                System.out.println(QUERY_CAR_VIA_EMAIL);
                while (resultSet.next()) {
                    Car carBase = new Car();
                    // todo remove duplicate code into method
                    carBase.setId(resultSet.getInt(1));
                    carBase.setVin(resultSet.getString(2));
                    carBase.setYear(resultSet.getInt(3));
                    carBase.setMiles(resultSet.getInt(4));
                    carBase.setMake(resultSet.getString(5));
                    carBase.setModel(resultSet.getString(6));
                    carBase.setCustomer_email(resultSet.getString(7));
                    cars.add(carBase);
                }
            } catch (SQLException e ) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        queryCarsViaEmailPrepStat.clearParameters();
                        resultSet.close();
                    }
                } catch (SQLException e ) {
                    e.printStackTrace();
                }
            }
        }
        return cars;
    }
// todo returning an empty list
    public List<Appointment> queryCustomerAppointments(String customerEmail) {
        List<Appointment> appointments = new ArrayList();
        if (customerEmail != null ) {
            try {
                System.out.println(QUERY_CUSTOMER_APPOINTMENTS);
                queryCustomerAppointmentsPrepStat.setString(1, customerEmail);
                ResultSet resultSet = queryCustomerAppointmentsPrepStat.executeQuery();
                queryCustomerAppointmentsPrepStat.clearParameters();
                while (resultSet.next()) {
                    System.out.println("Inside loop");
                    Appointment temp = new Appointment();
                    temp.setTimestamp(Timestamp.valueOf(resultSet.getString(1)));
                    temp.setVin(resultSet.getString(2));
                    temp.setMake(resultSet.getString(3));
                    temp.setModel(resultSet.getString(4));
                    temp.setYear(resultSet.getInt(5));
                    temp.setGarage(resultSet.getString(6));
                    appointments.add(temp);
                }

            } catch (SQLException e ) {
                e.printStackTrace();
            } finally {
//                if (resultSet != null) {
//                    try {
//                        resultSet.close();
//                    } catch (SQLException e ) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
        return appointments;
    }

    // query notes
    public List<Note> queryCarNotes(String vin) {
        List<Note> notes = new ArrayList<Note>();
        if (!vin.isEmpty()) {
            try {
                queryNotesPrepStat.setString(1, vin);
               ResultSet resultSet =  queryNotesPrepStat.executeQuery();
               while (resultSet.next()) {
                   Note note = new Note();
                   note.setNote(resultSet.getString(2));
                   note.setGarageName(resultSet.getString(3));
                   note.setEmail(resultSet.getString(4));
                   note.setTimestamp(Timestamp.valueOf(resultSet.getString(5)));
                   note.setId(resultSet.getInt(1));
                   notes.add(note);
               }
            } catch (SQLException e ){
                e.printStackTrace();
            }
        }
        return notes;
    }

    // insert into
    public int insertIntoGarage(String garageName, String garageLocation) throws SQLException{
        if (garageName == null || garageName.isEmpty() ||
                garageLocation == null || garageLocation.isEmpty()) {
            // invalid data was passed in
            return -1;
        }
        // check if the value is already in the database
            // if so return the value of the index
        queryGaragesPrepStat.setString(1, garageName);
        ResultSet resultSet = queryGaragesPrepStat.executeQuery();
        if (resultSet.next()) {
            // returning column one since that cointains the id value of garage
            return resultSet.getInt(1);
        } else {
            insertIntoGaragesPrepStat.setString(1, garageName);
            insertIntoGaragesPrepStat.setString(2, garageLocation);
            int affectedRows = insertIntoGaragesPrepStat.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Error inserting data");
            }
            ResultSet resultSetAfterUpdate = queryGaragesPrepStat.executeQuery();
            return resultSetAfterUpdate.getInt(1);
        }
    }

    // insert into customers
    public int insertIntoCustomer(
            String password, String email, String phone, String zip
    ) throws SQLException {
        if (!(isValidString(password) && isValidString(email) &&
                isValidString(phone) && isValidString(zip))) {
            return -1;
        } else {
            queryCustomersPrepStat.setString(1, email);
            ResultSet resultCheckIfExist = queryCustomersPrepStat.executeQuery();
            if (resultCheckIfExist.next()) {
//                System.out.println(resultCheckIfExist.getInt(1));
                return resultCheckIfExist.getInt(1);
            } else {
//                insertIntoCustomersPrepStat.setString(1, username);
                insertIntoCustomersPrepStat.setString(1, password);
                insertIntoCustomersPrepStat.setString(2, email);
                insertIntoCustomersPrepStat.setString(3, phone);
                insertIntoCustomersPrepStat.setString(4, zip);
                int affectedValues = insertIntoCustomersPrepStat.executeUpdate();
                if (affectedValues != 1) {
//                    System.out.println(affectedValues);
                    throw new SQLException("Error inserting data");
                }
                ResultSet updatedResultSet = queryCustomersPrepStat.executeQuery();
                if (updatedResultSet.next()) {
                    return updatedResultSet.getInt(1);
                }
            }
        }
        return -1;
    }

    public int insertEmployee(String email, String password, String phone,
                              int employeeType, int garage) throws SQLException{
        // todo add a guard clause
        queryEmployeesPrepStat.setString(1, email);
        ResultSet queryEmployee = queryEmployeesPrepStat.executeQuery();
        if (queryEmployee.next()) {
            return queryEmployee.getInt(1);
        } else {
            insertIntoEmployeesPrepStat.setString(1, password);
            insertIntoEmployeesPrepStat.setString(2, email);
            insertIntoEmployeesPrepStat.setString(3, phone);
            insertIntoEmployeesPrepStat.setInt(4, employeeType);
            insertIntoEmployeesPrepStat.setInt(5, garage);
            int affectedRows = insertIntoEmployeesPrepStat.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Error inserting data");
            }
            ResultSet employeeQuery = queryEmployeesPrepStat.executeQuery();
            return employeeQuery.getInt(1);
        }
    }


    public int insertCar(String vin, int year, int miles, int modelId, int customerId)  throws SQLException{
        // todo add a guard cluase
        queryCarsPrepStat.setString(1, vin);
        ResultSet preQuery = queryCarsPrepStat.executeQuery();
        if (preQuery.next()) {
            return preQuery.getInt(1);
        } else {
            insertIntoCarsPrepStat.setString(1, vin);
            insertIntoCarsPrepStat.setInt(2, modelId);
            insertIntoCarsPrepStat.setInt(3, year);
            insertIntoCarsPrepStat.setInt(4, miles);
            insertIntoCarsPrepStat.setInt(5, customerId);
            int affectedRows = insertIntoCarsPrepStat.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Error inserting data");
            }
            ResultSet postQuery = queryCarsPrepStat.executeQuery();
            return postQuery.getInt(1);
        }
    }

    public int insertNote(String note, int carId, int mechanicId) throws SQLException {
        // no need to check
        insertIntoNotesPrepStat.setString(1, note);
        insertIntoNotesPrepStat.setInt(2, carId);
        insertIntoNotesPrepStat.setInt(3, mechanicId);
        // check that only one line was affected
        int affectedRows = insertIntoNotesPrepStat.executeUpdate();
        if (affectedRows != 1) {
            throw new SQLException("Error inserting data");
        }
        // todo should combine with the other query
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_LAST_NOTE_ID)){
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return -1;
    }

    // insert appointment
    public int insertAppointment(String vin, String garageName, Timestamp timestamp) throws SQLException{
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        // ensure valid input
        if (vin == null || vin.isEmpty() || garageName == null || garageName.isEmpty() ||
                timestamp == null || timestamp.after(currentTimeStamp)) {
            System.out.println("Invalid Input");
            return -1;
        }
        // check if appointment already exist same, vin and timestamp
        querySingleAppointment.setString(1, timestamp.toString());
        querySingleAppointment.setString(2, vin);
        ResultSet resultSet = querySingleAppointment.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        // attempt to insert data
        // get id of car
        queryCarsPrepStat.setString(1, vin);
        ResultSet carResult = queryCarsPrepStat.executeQuery();
        if (!carResult.next()) {
            throw new SQLException("Error inserting data, invalid vin");
        }
        // get id of garage
        queryGaragesPrepStat.setString(1, garageName);
        ResultSet garageResult = queryGaragesPrepStat.executeQuery();
        if (!garageResult.next()) {
            throw new SQLException("Error inserting data, invalid garage name");
        }

        insertIntoAppointmentsPrepStat.setInt(1, carResult.getInt(1));
        insertIntoAppointmentsPrepStat.setInt(2, garageResult.getInt(1));
        int affectedRows = insertIntoAppointmentsPrepStat.executeUpdate();
        if (affectedRows != 1)  {
            throw new SQLException("Error updating table");
        }

        resultSet = querySingleAppointment.executeQuery();
        return resultSet.getInt(1);
    }

    // delete make
    public boolean deleteFromMakeTable(String name) throws SQLException{
        if (name == null || name.isEmpty()) {
            return false;
        }
        deleteItemFromMakeTablePrepStat.setString(1, name);
        return deleteItemFromMakeTablePrepStat.execute();
    }

    // delete model
    public boolean deleteModel(String modelName) throws SQLException {
        if (modelName == null || modelName.isEmpty()) {
            return false;
        }
        deleteModelPrepStat.setString(1, modelName);
        return deleteModelPrepStat.execute();
    }

    public boolean deleteMake(String makeName) throws SQLException {
        if (makeName == null || makeName.isEmpty()) {
            return false;
        } else {
            deleteItemFromMakeTablePrepStat.setString(1, makeName);
            boolean holder =  deleteItemFromMakeTablePrepStat.execute();
            System.out.println(holder);
            return holder;
        }
    }

    public boolean deleteCustomer(String email) throws SQLException{
        if (email == null || email.isEmpty()) {
            throw new SQLException("User does not exist");
        }
        deleteCustomerPrepStat.setString(1, email);
        return deleteCustomerPrepStat.execute();
    }

    public boolean deleteCar(String vin) throws SQLException{
        if (vin == null || vin.isEmpty()) {
            throw new SQLException("Car does not exist");
        }
        deleteCarPrepStat.setString(1, vin);
        return deleteCarPrepStat.execute();
    }

    public boolean deleteGarage(String garageName) throws SQLException {
        if (garageName == null || garageName.isEmpty()) {
            throw new SQLException("Garage does not exist");
        }
        deleteGaragePrepStat.setString(1, garageName);
        return deleteGaragePrepStat.execute();
    }

    public boolean deleteNote(int noteId) throws SQLException{
        if (noteId <= 1) {
            throw new SQLException("Note id is invaild");
        }
        deleteNotePrepStat.setInt(1, noteId);
        return deleteNotePrepStat.execute();
    }


    public boolean isValidString(String value)  {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return true;
    }



}
