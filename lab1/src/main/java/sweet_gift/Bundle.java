package sweet_gift;

import java.util.ArrayList;

import sweet_gift.Sweets.Sweet;

public class Bundle {
    public ArrayList<Sweet> getSweets() {
        return sweets;
    }

    public void setSweets(ArrayList<Sweet> sweets) {
        this.sweets = sweets;
    }

    private ArrayList<Sweet> sweets = new ArrayList<>();

    public int weight() {
        int total = 0;
        for (Sweet s : sweets) {
            total += s.getWeight();
        }
        return total;
    }

    public void sortSugar() {
        int size = sweets.size();
        // bubble sort
        for (int k = 0; k < size; k += 1) {
            for (int p = 1; p < size; p += 1) {
                Sweet prev = sweets.get(p - 1);
                Sweet curr = sweets.get(p);
                if (prev.getSugar() > curr.getSugar()) {
                    // swap
                    sweets.set(p - 1, curr);
                    sweets.set(p, prev);
                }
            }
        }
    }

    public Sweet find(Range range) {
        for (Sweet s : sweets) {
            if (range.in(s.getSugar())) {
                return s;
            }
        }
        return null;
    }
}
