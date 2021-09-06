package digital.number.scanner.service;

import org.junit.Before;

import java.io.IOException;
import java.util.List;

public class ScannerServiceIntegrationTest extends BaseScannerServiceIntegrationTest {
    private ScannerObjectFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new ScannerObjectFactory(ScannerConfig.DEFAULT_CONFIG);
    }

    @Override
    protected List<String> performScanning(String inputFilePath) {
        try {
            return factory.getScannerService().parse(inputFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
