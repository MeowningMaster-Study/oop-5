package sweet_gift;

public class Range {
    int min;
    int max;

    Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean in(int v) {
        return v >= min && v < max;
    }

    public String toString() {
        return String.format("[%d, %d)", min, max);
    }
}
