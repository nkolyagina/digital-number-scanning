package digital.number.scanner.service;

import java.util.List;
import java.util.stream.Stream;

public interface SymbolReader {
    Stream<char[][]> getMatrices(Chunk chunk);
}
