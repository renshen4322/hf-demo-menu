package computermethod;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MapIfTest {
    public static void main(String[] args) {
        LinkedList<Integer> llist = new LinkedList<>();
        llist.push(123);
        llist.push(432);
        llist.push(167);
        llist.push(864);
        llist.push(345);
        llist.push(323);
        AtomicInteger a = new AtomicInteger();
        for (Integer element : llist) {
            if (a.incrementAndGet() == 3) {
                System.out.println(element);
            }
        }
    }
}
