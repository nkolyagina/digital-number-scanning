package digital.number.scanner.service;

import java.util.List;
import java.util.Objects;

public class Chunk {
    private final List<String> lines;
    private boolean valid;

    public Chunk(List<String> lines, boolean valid) {
        this.lines = Objects.requireNonNull(lines);
        this.valid = valid;
    }

    public List<String> getLines() {
        return lines;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chunk chunk = (Chunk) o;
        return isValid() == chunk.isValid() &&
                getLines().equals(chunk.getLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLines(), isValid());
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "lines=" + lines +
                ", valid=" + valid +
                '}';
    }
}
