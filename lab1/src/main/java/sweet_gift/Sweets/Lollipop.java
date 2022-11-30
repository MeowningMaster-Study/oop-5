package sweet_gift.Sweets;

public class Lollipop extends Sweet {
    public boolean isWithGum() {
        return withGum;
    }

    public void setWithGum(boolean withGum) {
        this.withGum = withGum;
    }

    private boolean withGum = false;

    public Lollipop(String name, int weight, int sugar) {
        super(name, weight, sugar);
    }
}
