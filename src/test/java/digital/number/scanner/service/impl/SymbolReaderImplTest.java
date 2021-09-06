package digital.number.scanner.service.impl;

import digital.number.scanner.service.Chunk;
import digital.number.scanner.service.ScannerConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SymbolReaderImplTest {
    private SymbolReaderImpl symbolReader;
    private Chunk chunk;
    private List<char[][]> expectedMatrices;

    @Before
    public void setUp() throws Exception {
        ScannerConfig config = new ScannerConfig(3, 6, 3);
        symbolReader = new SymbolReaderImpl(config);
        chunk = new Chunk(Arrays.asList("|||   ", " | ___", "||||||"), true);
        expectedMatrices = new ArrayList<>();
        expectedMatrices.add(new char[][]{
                {'|', '|', '|'},
                {' ', '|', ' '},
                {'|', '|', '|'},
        });
        expectedMatrices.add(new char[][]{
                {' ', ' ', ' '},
                {'_', '_', '_'},
                {'|', '|', '|'},
        });
    }

    @Test
    public void getDigits() {
        List<char[][]> actual = symbolReader.getMatrices(chunk).collect(Collectors.toList());
        Assert.assertArrayEquals(expectedMatrices.toArray(), actual.toArray());
    }

    @Test
    public void extractDigitBlock() {
        Assert.assertArrayEquals(expectedMatrices.get(0), symbolReader.extractDigitBlock(chunk, 0));
        Assert.assertArrayEquals(expectedMatrices.get(1), symbolReader.extractDigitBlock(chunk, 1));
    }
}