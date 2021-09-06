package digital.number.scanner.service;

import java.io.IOException;
import java.util.List;

public interface ScannerService {
    void parseAndWrite(String inputFilePath) throws IOException;
    List<String> parse(String inputFilePath) throws IOException;
}
