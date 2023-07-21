package computermethod;

import java.util.List;
import java.util.stream.Collectors;

public class TestList {
    public static void main(String[] args) {
        //然后调用mergeLists，做合并。
    }

    public static <T> List<T> mergeLists(List<T>... lists) {
        Class clazz = lists[0].getClass();
        List<T> merge = null;
        try {
            merge = (List<T>) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0, len = lists.length; i < len; i++) {
            merge.addAll(lists[i]);
        }
        if (merge.size() > 0) {
            merge = merge.stream().sorted().collect(Collectors.toList());
        }
        //

        return merge;
    }
}
