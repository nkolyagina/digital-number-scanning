package digital.number.scanner.service.impl;

import digital.number.scanner.service.FileParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FileParserImpl  implements FileParser {
    @Override
    public Stream<String> readLines(String inputFilePath) throws IOException {
        return Files.lines(new File(inputFilePath).toPath());
    }
}
