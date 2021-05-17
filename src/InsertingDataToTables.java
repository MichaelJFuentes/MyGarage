import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertingDataToTables {
    // method to populate the make table
    public static final String MAKES_FILE = "src\\carMakes";
    public static final String MODELS_FILE_CSV = "carModels.txt";

    public static List<String> readFileData(String fileName) {
        ArrayList<String> carMakers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                carMakers.add(temp);
            }
            return carMakers;
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e ) {
            return carMakers;
        }
        return carMakers;
    }
//    public static void populateMakeTable(List<String> data) {
//        // load the data into the call methods (multi thread)
//        if (data != null) {
//            for (String i : data) {
//                System.out.println(i);
//                CreateSeedDataSQLFile.getInstance().insertIntoMakeDatabaseTable(i);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e ) {
//
//                }
//            }
//        }
//
//    }

//    public static List<CarModel> readCSVFile(String fileLocation) {
//        List<CarModel> myArray = new ArrayList<>();
//        String line;
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
//            while ((line = reader.readLine()) != null) {
//                myArray.add(getRecordFromLine(line));
//            }
//            return myArray;
//        } catch (IOException e ) {
//
//        }
//        return myArray;
//    }

    public static void getRecordFromLine(String line) {
//        String[] splitLine = line.split(",");
        System.out.println(line);
        String patterString = "\\[\\{\"brand\": \"(.*?)\", \"models\": \\[(.*?)]}";
        Pattern pattern = Pattern.compile(patterString);
        Matcher matcher = pattern.matcher("[{\"brand\": \"Seat\", \"models\": [\"Alhambra\", \"Altea\", \"Altea XL\", \"Arosa\", \"Cordoba\", \"Cordoba Vario\", \"Exeo\", \"Ibiza\", \"Ibiza ST\", \"Exeo ST\", \"Leon\", \"Leon ST\", \"Inca\", \"Mii\", \"Toledo\"]},");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }

}
