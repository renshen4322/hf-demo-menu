package computermethod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class mergeListTest {
    public static void main(String[] args) {
        listMerge();
        //linkedlistMerge();
    }

    private static void linkedlistMerge() {
        LinkedList<Integer> L1 = new LinkedList<>();
        L1.add(1);
        L1.add(3);
        L1.add(5);
        LinkedList<Integer> L2 = new LinkedList<>();
        L2.add(2);
        L2.add(4);
        L2.add(6);
        LinkedList<Integer> merged = new LinkedList<>();
        merged.addAll(L1);
        merged.addAll(L2);
        System.out.println("L1 : " + L1);
        System.out.println("L2 : " + L2);
        System.out.println("Merged : " + merged);
    }

    private static void listMerge() {
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(1);
        l1.add(3);
        l1.add(5);
        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(2);
        l2.add(4);
        l2.add(6);
        List<Integer> merge = new ArrayList<Integer>();
        merge.addAll(l1);
        merge.addAll(l2);
        System.out.println("L1 : " + l1);
        System.out.println("L2 : " + l2);
        merge = merge.stream().sorted().collect(Collectors.toList());
        System.out.println("Merged : " + merge);
    }
}
