package digital.number.scanner.service.impl;

import digital.number.scanner.service.Chunk;
import digital.number.scanner.service.Chunker;
import digital.number.scanner.service.ScannerConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ChunkerImpl implements Chunker {

    private final ScannerConfig config;

    public ChunkerImpl(ScannerConfig config) {
        this.config = Objects.requireNonNull(config);
    }

    @Override
    public Stream<Chunk> chunkify(Stream<String> lines) {
        Iterator<String> lineIterator = lines.iterator();
        Iterator<Chunk> chunkIterator = new Iterator<Chunk>() {
            @Override
            public boolean hasNext() {
                return lineIterator.hasNext();
            }

            @Override
            public Chunk next() {
                List<String> lines = new ArrayList<>(config.getChunkRowCount());
                boolean valid = true;
                while (lineIterator.hasNext()) {
                    String line = lineIterator.next();

                    if (lines.size() >= config.getChunkRowCount() && line.trim().isEmpty()){
                        break;
                    } else {
                        lines.add(line);
                        valid = valid && (line.length() == config.getChunkRowLength());
                    }
                }
                valid = valid && (lines.size() == config.getChunkRowCount());
                return new Chunk(lines, valid);
            }
        };
        return StreamSupport.stream(((Iterable<Chunk>) () -> chunkIterator).spliterator(), false);
    }
}
