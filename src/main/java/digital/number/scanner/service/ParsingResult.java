package digital.number.scanner.service;

import java.util.Arrays;
import java.util.Objects;

public class ParsingResult {
    public static final ParsingResult INVALID_CHUNK_RESULT = new ParsingResult(new char[0], false);
    private final char[] digits;
    private final boolean valid;

    public ParsingResult(char[] digits, boolean valid) {
        this.digits = Objects.requireNonNull(digits);
        this.valid = valid;
    }

    public char[] getDigits() {
        return digits;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParsingResult that = (ParsingResult) o;
        return isValid() == that.isValid() &&
                Arrays.equals(getDigits(), that.getDigits());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isValid());
        result = 31 * result + Arrays.hashCode(getDigits());
        return result;
    }

    @Override
    public String toString() {
        return "ParsingResult{" +
                "digits=" + Arrays.toString(digits) +
                ", valid=" + valid +
                '}';
    }
}
