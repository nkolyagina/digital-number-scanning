package digital.number.scanner.service.impl;

import digital.number.scanner.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScannerServiceImpl implements ScannerService {

    private final FileParser parser;
    private final Chunker chunker;
    private final Processor processor;
    private final OutputWriter writer;

    public ScannerServiceImpl(FileParser parser, Chunker chunker, Processor processor, OutputWriter writer) {
        this.parser = Objects.requireNonNull(parser);
        this.chunker = Objects.requireNonNull(chunker);
        this.processor = Objects.requireNonNull(processor);
        this.writer = Objects.requireNonNull(writer);
    }

    @Override
    public void parseAndWrite(String inputFilePath) throws IOException {
        doParse(inputFilePath).forEach(writer::write);
    }

    @Override
    public List<String> parse(String inputFilePath) throws IOException {
        return doParse(inputFilePath).map(writer::resultToString).collect(Collectors.toList());
    }

    Stream<ParsingResult> doParse(String inputFilePath) throws IOException{
        return chunker.chunkify(parser.readLines(inputFilePath))
                .map(processor::processChunk);
    }
}
