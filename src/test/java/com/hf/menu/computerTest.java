package com.hf.menu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class computerTest {

    @Test
    public void arrayResult() {
        getResult(8);

    }


    //   / * 13、数组中二二组合 为一个指定的数
// * @param result
// */
    public void getResult(int result) {
        int[] arr = new int[]{2, 3, 5, 4};

        for (int i = 0; i < arr.length; i++) {
            int startItem = arr[i];

            for (int j = 0; j < arr.length; j++) {
                if (i == j)
                    continue;
                int endItem = arr[j];
                if ((startItem + endItem) == result) {
                    System.out.println(startItem + "+" + endItem + "=" + result);
                    int index1 = Arrays.binarySearch(arr, startItem);
                    System.out.println("startItem 数组中的位置：" + index1);
                    int index2 = Arrays.binarySearch(arr, endItem);
                    System.out.println("endItem 数组中的位置：" + index2);
                }


            }

        }
    }

}
