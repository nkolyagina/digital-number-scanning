package digital.number.scanner.service.impl;

import digital.number.scanner.service.ParsingResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OutputWriterImplTest {
    @Mock
    private PrintStream printStream;

    private OutputWriterImpl outputWriter;

    @Before
    public void setUp() throws Exception {
        outputWriter = new OutputWriterImpl(printStream);
    }

    @Test
    public void write() {
        ParsingResult result = new ParsingResult(new char[]{'1', '2'}, true);
        outputWriter.write(result);
        Mockito.verify(printStream).println("12");
    }

    @Test
    public void writeInvalid() {
        ParsingResult result = new ParsingResult(new char[]{'1', '?'}, false);
        outputWriter.write(result);
        Mockito.verify(printStream).println("1?ILL");
    }

    @Test
    public void writeInvalidChunkResult() {
        ParsingResult result = ParsingResult.INVALID_CHUNK_RESULT;
        outputWriter.write(result);
        Mockito.verify(printStream).println("ILL");
    }
}