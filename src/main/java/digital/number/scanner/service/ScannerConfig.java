package digital.number.scanner.service;

public class ScannerConfig {
    public static final ScannerConfig DEFAULT_CONFIG = new ScannerConfig(3, 27, 3);

    private final int chunkRowCount;
    private final int chunkRowLength;
    private final int digitWidth;
    private final int digitCount;

    public ScannerConfig(int chunkRowCount, int chunkRowLength, int digitWidth) {
        this.chunkRowCount = chunkRowCount;
        this.chunkRowLength = chunkRowLength;
        this.digitWidth = digitWidth;
        this.digitCount = chunkRowLength / digitWidth;
    }

    public int getChunkRowCount() {
        return chunkRowCount;
    }

    public int getChunkRowLength() {
        return chunkRowLength;
    }

    public int getDigitWidth() {
        return digitWidth;
    }

    public int getDigitCount() {
        return digitCount;
    }
}
