package sweet_gift.Sweets;

public class Cookie extends Sweet {
    public Filling getFilling() {
        return filling;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    private Filling filling = Filling.NONE;

    public Cookie(String name, int weight, int sugar) {
        super(name, weight, sugar);
    }
}