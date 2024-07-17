package chiarafais.capstoneBE.readCSV;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//@Service
//public class CSVreader {
//    public List<String[]> readCsv(Path path) throws Exception {
//        try (Reader reader = Files.newBufferedReader(path);
//             CSVReader csvReader = new CSVReaderBuilder(reader)
//                     .withCSVParser(new CSVParserBuilder()
//                             .withSeparator(';')
//                             .build())
////                     .withSkipLines(1)
//                     .build()) {
//            return csvReader.readAll();
//        }
//    }
//}
