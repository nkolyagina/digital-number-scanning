package digital.number.scanner.service.impl;

import digital.number.scanner.service.OutputWriter;
import digital.number.scanner.service.ParsingResult;

import java.io.PrintStream;
import java.util.Arrays;

public class OutputWriterImpl implements OutputWriter {
    private final PrintStream printStream;

    public OutputWriterImpl() {
        this(System.out);
    }

    OutputWriterImpl(PrintStream printStream) {
        this.printStream = printStream;
    }


    @Override
    public void write(ParsingResult result) {
        printStream.println(resultToString(result));
    }

    @Override
    public String resultToString(ParsingResult result) {
        return new String(result.getDigits()) + (result.isValid() ? "" : "ILL");
    }
}
