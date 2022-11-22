package sweet_gift;

import sweet_gift.Sweets.Cookie;
import sweet_gift.Sweets.Filling;
import sweet_gift.Sweets.Lolipop;
import sweet_gift.Sweets.Sweet;

public class App {
    public static void main(String[] args) {
        Lolipop l1 = new Lolipop("L1", 14, 1);
        Lolipop l2 = new Lolipop("L2", 20, 5);
        Lolipop l3 = new Lolipop("L3", 10, 3);
        l3.withGum = true;

        Cookie c1 = new Cookie("C1", 25, 5);
        Cookie c2 = new Cookie("C2", 40, 10);
        Cookie c3 = new Cookie("C3", 18, 4);
        c3.filling = Filling.Milk;

        Bundle bundle = new Bundle();
        bundle.add(l1);
        bundle.add(l2);
        bundle.add(l3);
        bundle.add(c1);
        bundle.add(c2);
        bundle.add(c3);

        int totalWeight = bundle.weight();
        System.out.printf("Total weight: %d\n", totalWeight);

        bundle.sortSugar();
        System.out.printf("Bundle sorted by sugar: %s\n", bundle);

        Range searchRange = new Range(10, 12);
        Sweet found = bundle.find(searchRange);
        System.out.printf("Found in %s: %s\n", searchRange, found);
    }
}