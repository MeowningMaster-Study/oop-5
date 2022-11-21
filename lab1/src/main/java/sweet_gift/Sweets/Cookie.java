package sweet_gift.Sweets;

public class Cookie extends Sweet {
    public Filling filling = Filling.None;

    public Cookie(String name, int weight, int sugar) {
        super(name, weight, sugar);
    }
}