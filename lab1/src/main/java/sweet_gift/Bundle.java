package sweet_gift;

import java.util.ArrayList;

import sweet_gift.Sweets.Sweet;

public class Bundle extends ArrayList<Sweet> {
    public int weight() {
        int total = 0;
        for (Sweet s : this) {
            total += s.getWeight();
        }
        return total;
    }

    public void sortSugar() {
        int size = this.size();
        // bubble sort
        for (int k = 0; k < size; k += 1) {
            for (int p = 1; p < size; p += 1) {
                Sweet prev = this.get(p - 1);
                Sweet curr = this.get(p);
                if (prev.getSugar() > curr.getSugar()) {
                    // swap
                    this.set(p - 1, curr);
                    this.set(p, prev);
                }
            }
        }
    }

    public Sweet find(Range range) {
        for (Sweet s : this) {
            if (range.in(s.getSugar())) {
                return s;
            }
        }
        return null;
    }
}
