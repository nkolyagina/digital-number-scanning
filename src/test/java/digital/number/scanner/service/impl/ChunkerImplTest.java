package digital.number.scanner.service.impl;

import digital.number.scanner.service.Chunk;
import digital.number.scanner.service.ScannerConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class ChunkerImplTest {
    private ChunkerImpl chunker;

    @Before
    public void setUp() throws Exception {
        ScannerConfig config = new ScannerConfig(1, 3, 3);
        chunker = new ChunkerImpl(config);

    }

    @Test
    public void chunkify() {
        List<String> lines = Arrays.asList("|||", " ", "___");
        List<Chunk> expectedChunks = Arrays.asList(
                new Chunk(singletonList("|||"), true),
                new Chunk(singletonList("___"), true));
        Assert.assertEquals(expectedChunks, chunker.chunkify(lines.stream()).collect(Collectors.toList()));
    }

    @Test
    public void chunkifyWithInvalidLines() {
        List<String> lines = Arrays.asList("||||", " ", "___");
        List<Chunk> expectedChunks = Arrays.asList(
                new Chunk(singletonList("||||"), false),
                new Chunk(singletonList("___"), true));
        Assert.assertEquals(expectedChunks, chunker.chunkify(lines.stream()).collect(Collectors.toList()));
    }

    @Test
    public void chunkifyWithoutSeparator() {
        List<String> lines = Arrays.asList("|||",  "___");
        List<Chunk> expectedChunks = singletonList(
                new Chunk(lines, false));
        Assert.assertEquals(expectedChunks, chunker.chunkify(lines.stream()).collect(Collectors.toList()));
    }
}