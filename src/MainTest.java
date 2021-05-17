import java.sql.SQLException;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        CreateSeedDataSQLFile temp = CreateSeedDataSQLFile.getInstance();
        temp.open();
//        List<String> myList = InsertingDataToTables.readFileData(InsertingDataToTables.MAKES_FILE);
//        InsertingDataToTables.populateMakeTable(myList);
//        InsertingDataToTables.readCSVFile(InsertingDataToTables.MODELS_FILE_CSV);
        InsertingDataToTables.getRecordFromLine("test");
        temp.close();

    }
}
