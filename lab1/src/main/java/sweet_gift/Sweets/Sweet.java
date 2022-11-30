package sweet_gift.Sweets;

public abstract class Sweet {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight;

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    private int sugar;

    public Sweet(String name, int weight, int sugar) {
        this.name = name;
        this.weight = weight;
        this.sugar = sugar;
    }

    public String toString() {
        return String.format("%s (%d)", name, sugar);
    }
}
