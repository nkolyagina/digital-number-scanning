package digital.number.scanner.service;

import java.util.List;
import java.util.stream.Stream;

public interface Chunker {
    Stream<Chunk> chunkify(Stream<String> lines);
}
