package digital.number.scanner.service;

import java.util.stream.IntStream;

public interface OutputWriter {
    void write(ParsingResult result);
    String resultToString(ParsingResult result);
}
