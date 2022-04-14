package utils;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.exceptions.CsvException;
import java.util.List;

public class csvHelper {
    public static Object[][] readCsvFile(String fileName) throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader(fileName));
        List<String[]> csvData = csvReader.readAll();
        Object[][] result = new Object[csvData.size()][];
        for (int i = 0; i < csvData.size(); i++) {
            result[i] = csvData.get(i);
        }
        return result;
    }
}
