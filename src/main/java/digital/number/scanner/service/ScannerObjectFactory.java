package digital.number.scanner.service;

import digital.number.scanner.service.impl.*;

import java.util.Objects;

public class ScannerObjectFactory {
    private final ScannerConfig config;
    private final FileParser parser;
    private final Chunker chunker;
    private final Processor processor;
    private final SymbolReader symbolReader;
    private final SymbolMatcher symbolMatcher;
    private final OutputWriter outputWriter;
    private final ScannerService scannerService;

    public ScannerObjectFactory(ScannerConfig config) {
        this.config = Objects.requireNonNull(config);
        parser = new FileParserImpl();
        chunker = new ChunkerImpl(config);
        symbolReader = new SymbolReaderImpl(config);
        symbolMatcher = new DefaultSymbolMatcher();
        processor = new ProcessorImpl(config, symbolReader, symbolMatcher);
        outputWriter = new OutputWriterImpl();
        scannerService = new ScannerServiceImpl(parser, chunker, processor, outputWriter);
    }

    public ScannerConfig getConfig() {
        return config;
    }

    public FileParser getParser() {
        return parser;
    }

    public Chunker getChunker() {
        return chunker;
    }

    public Processor getProcessor() {
        return processor;
    }

    public SymbolReader getSymbolReader() {
        return symbolReader;
    }

    public SymbolMatcher getSymbolMatcher() {
        return symbolMatcher;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public ScannerService getScannerService() {
        return scannerService;
    }
}
