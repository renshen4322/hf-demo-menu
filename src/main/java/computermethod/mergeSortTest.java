package computermethod;

import java.util.ArrayList;
import java.util.List;

public class mergeSortTest {
    public static void main(String[] args) {
        int[] a = {2,5,7,8};
        int[] b = {4,6,66,77,89};
        List<Integer> c = mergeArray(a, b);
        System.out.println(c.toString());

    }
    //归并有序数组
    public static List<Integer> mergeArray(int [] a,int [] b){
        //归并后数组
        List<Integer> list = new ArrayList<Integer>();
        //待归并数组索引
        int i = 0,j = 0;

        //遍历两个数组，寻找最小值元素进行归并
        while (i < a.length && j < b.length) {
            //分情况判断 -- 选定最小值后放入归并数组 当前数组索引前移
            if (a[i] < b[j]) {
                list.add(a[i++]);
            } else if (a[i] > b[j]){
                list.add(b[j++]);
            } else {
                //若比较的数组元素相同 归并后两数组索引均前移
                list.add(b[j++]);
                i++;
            }
        }
        //补充其他元素进入归并数组 经过上述最小值归并 循环结束后必定有一数组已经完全归并结束
        while (i < a.length) {
            list.add(a[i++]);
        }
        while (j < b.length) {
            list.add(b[j++]);
        }
        return list;
    }


}
