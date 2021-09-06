package digital.number.scanner.service.impl;

import digital.number.scanner.service.*;

import java.util.Iterator;
import java.util.Objects;

public class ProcessorImpl implements Processor {

    private final ScannerConfig config;
    private final SymbolReader reader;
    private final SymbolMatcher matcher;

    public ProcessorImpl(ScannerConfig config, SymbolReader reader, SymbolMatcher matcher) {
        this.config = Objects.requireNonNull(config);
        this.reader = Objects.requireNonNull(reader);
        this.matcher = Objects.requireNonNull(matcher);
    }


    @Override
    public ParsingResult processChunk(Chunk chunk) {
        if (!chunk.isValid()) {
            return ParsingResult.INVALID_CHUNK_RESULT;
        }

        char[] digits = new char[config.getDigitCount()];
        boolean valid = true;

        Iterator<Character> iterator = reader.getMatrices(chunk).map(matcher::match).iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            digits[i] = iterator.next();
            valid = valid && (digits[i] != '?');
        }

        return new ParsingResult(digits, valid);
    }
}
