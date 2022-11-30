package sweet_gift;

import sweet_gift.Sweets.Cookie;
import sweet_gift.Sweets.Filling;
import sweet_gift.Sweets.Lollipop;
import sweet_gift.Sweets.Sweet;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Lollipop l1 = new Lollipop("L1", 14, 1);
        Lollipop l2 = new Lollipop("L2", 20, 5);
        Lollipop l3 = new Lollipop("L3", 10, 3);
        l3.setWithGum(true);

        Cookie c1 = new Cookie("C1", 25, 5);
        Cookie c2 = new Cookie("C2", 40, 10);
        Cookie c3 = new Cookie("C3", 18, 4);
        c3.setFilling(Filling.MILK);

        Bundle bundle = new Bundle();
        ArrayList<Sweet> sweets = bundle.getSweets();
        sweets.add(l1);
        sweets.add(l2);
        sweets.add(l3);
        sweets.add(c1);
        sweets.add(c2);
        sweets.add(c3);

        int totalWeight = bundle.weight();
        System.out.printf("Total weight: %d\n", totalWeight);

        bundle.sortSugar();
        System.out.printf("Bundle sorted by sugar: %s\n", bundle);

        Range searchRange = new Range(10, 12);
        Sweet found = bundle.find(searchRange);
        System.out.printf("Found in %s: %s\n", searchRange, found);
    }
}
