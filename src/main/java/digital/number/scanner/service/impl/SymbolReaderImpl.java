package digital.number.scanner.service.impl;

import digital.number.scanner.service.Chunk;
import digital.number.scanner.service.ScannerConfig;
import digital.number.scanner.service.SymbolReader;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SymbolReaderImpl implements SymbolReader {
    private final ScannerConfig config;

    public SymbolReaderImpl(ScannerConfig config) {
        this.config = Objects.requireNonNull(config);
    }

    @Override
    public Stream<char[][]> getMatrices(Chunk chunk) {
        return IntStream.range(0,config.getDigitCount())
                .mapToObj( i -> extractDigitBlock(chunk, i));
    }

    char[][] extractDigitBlock(Chunk chunk, int digitIndex) {
        char[][] output = new char[config.getChunkRowCount()][config.getDigitWidth()];
        for (int i = 0; i < config.getChunkRowCount(); i++) {
            String line = chunk.getLines().get(i);
            int offset = digitIndex * config.getDigitWidth();
            for (int j = 0; j < config.getDigitWidth(); j++) {
                output[i][j] = line.charAt(offset + j);
            }
        }
        return output;
    }

}
