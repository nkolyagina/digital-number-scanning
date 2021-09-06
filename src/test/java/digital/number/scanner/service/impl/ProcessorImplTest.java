package digital.number.scanner.service.impl;

import digital.number.scanner.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorImplTest {

    private ProcessorImpl processor;

    @Mock
    private SymbolReader reader;

    @Mock
    private SymbolMatcher matcher;


    @Before
    public void setUp() throws Exception {
        ScannerConfig config = new ScannerConfig(1, 2, 1);
        processor = new ProcessorImpl(config, reader, matcher);
    }

    @Test
    public void processChunk() {
        Chunk chunk = new Chunk(Arrays.asList("||", "__"), true);
        char[][] matrix1 = {{'|', '|'}};
        char[][] matrix2 = {{'_', '_'}};
        when(reader.getMatrices(chunk))
                .thenReturn(Stream.of(matrix1, matrix2));
        when(matcher.match(argThat(array -> Arrays.deepEquals(array, matrix1))))
                .thenReturn('1');
        when(matcher.match(argThat(array -> Arrays.deepEquals(array, matrix2))))
                .thenReturn('2');
        ParsingResult result = processor.processChunk(chunk);
        assertTrue(result.isValid());
        assertArrayEquals(new char[] {'1', '2'}, result.getDigits());
    }
}