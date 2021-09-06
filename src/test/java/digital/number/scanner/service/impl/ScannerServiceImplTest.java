package digital.number.scanner.service.impl;

import digital.number.scanner.service.*;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ScannerServiceImplTest {
    public static final String FILE_PATH = "filePath";

    private ParsingResult expectedResult = new ParsingResult(new char[]{'1'}, true);

    @Mock
    private FileParser parser;
    @Mock
    private Chunker chunker;
    @Mock
    private Processor processor;
    @Mock
    private OutputWriter writer;

    private ScannerServiceImpl scannerService;

    @Before
    public void setUp() throws Exception {
        scannerService = new ScannerServiceImpl(parser, chunker, processor, writer);
        List<String> lines = Arrays.asList("|||",  "___");
        List<Chunk> chunks = singletonList(
                new Chunk(lines, true));


        Stream<String> lineStream = lines.stream();
        Mockito.when(parser.readLines(FILE_PATH)).thenReturn(lineStream);
        Mockito.when(chunker.chunkify(same(lineStream))).thenReturn(chunks.stream());
        Mockito.when(processor.processChunk(chunks.get(0))).thenReturn(expectedResult);
    }

    @Test
    public void parseAndWrite() throws IOException {
        scannerService.parseAndWrite(FILE_PATH);
        Mockito.verify(writer).write(expectedResult);
    }

    @Test
    public void parse() throws IOException {
        List<String> expected = Arrays.asList("1");
        Mockito.when(writer.resultToString(expectedResult)).thenReturn("1");
        List<String> result = scannerService.parse(FILE_PATH);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void doParse() throws IOException {
        List<ParsingResult> actualResults = scannerService.doParse(FILE_PATH).collect(Collectors.toList());
        Assert.assertEquals(singletonList(expectedResult), actualResults);
    }
}