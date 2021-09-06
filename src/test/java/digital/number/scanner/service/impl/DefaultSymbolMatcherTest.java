package digital.number.scanner.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultSymbolMatcherTest {
    private DefaultSymbolMatcher matcher;

    @Before
    public void setUp() throws Exception {
        matcher = new DefaultSymbolMatcher();
    }

    @Test
    public void match() {
        char[][] input = {
                {' ', ' ', ' '},
                {' ', ' ', '|'},
                {' ', ' ', '|'}
        };

        Assert.assertEquals('1', matcher.match(input));


    }
}