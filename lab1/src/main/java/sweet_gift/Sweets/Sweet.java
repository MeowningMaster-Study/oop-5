package sweet_gift.Sweets;

public class Sweet {
    public String name;
    public int weight;
    public int sugar;

    public Sweet(String name, int weight, int sugar) {
        this.name = name;
        this.weight = weight;
        this.sugar = sugar;
    }

    public String toString() {
        return String.format("%s (%d)", name, sugar);
    }
}
