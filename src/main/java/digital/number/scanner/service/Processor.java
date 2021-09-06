package digital.number.scanner.service;

import java.util.List;

public interface Processor {
    ParsingResult processChunk(Chunk chunk);
}
