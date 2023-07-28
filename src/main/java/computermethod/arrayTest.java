package computermethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class arrayTest {

    public static void main(String[] args) {
        //getSumGroupResult(10);
        // caml20();
        // numBaoTa();
        // camlBall8();
        //camlFenfu();
        //  getSum(100);
        //jieChen20();
        //caml100();
        // camlJieChen01();
        //System.out.println(calculateSum(100));
        //houziMeet();
        // System.out.println(funJieTi2(50));
        //elementCount();
        // String[]arr = new String[]{"d","c","k","f"};
        // reverseArray(arr);
       // System.out.println(foo(30));

        int[] nums = {
                4, 5, 6, 9, 26, 39, 1,
                18, 24, 21, 19, 33};
        int maxProfit = solution(nums);
        System.out.println(
                "Max profit is: " + maxProfit);
        extracted();

        getResult(10);
    }
    /**
     * 数组中二二组合 为一个指定的数
     * @param result
     */
    public static void getResult(int result) {
        int[] arr = new int[]{0, 2, 3, 1, 4, 10, 23, 7, 8, 9, 6, 3};
        for (int i = 0; i < arr.length; i++) {
            int startItem = arr[i];
            for (int j = 0; j < arr.length; j++) {
                if (i == j)
                    continue;
                int endItem = arr[j];
                if ((startItem + endItem) == result)
                    System.out.println(startItem + "+" + endItem + "=" + result);

            }

        }
    }

    private static void extracted() {
        int[] ints = {9, 4, 9, 2, 1, 9, 9, 9};
        List<Integer> lt = new ArrayList<>();
        for (int i = 0; i <= ints.length - 1; i++) {
            lt.add(ints[i]);
        }
        System.out.println("=========================lt=======" + lt);
        int j = judge(ints);
        if (j == -1) {
            System.out.println("-1");
        } else {
            System.out.println("支配者是:" + ints[j]);
            int result = ints[j];
            List<Integer> list = new ArrayList<>();
            for (int m = 0; m < lt.size(); m++) {
                if (result == lt.get(m)) {
                    list.add(m);
                }
            }
            System.out.println(list);
            Object[] objs = list.toArray();
            ////产生0-(arr.length-1)的整数值,也是数组的索引
            int index = (int) (Math.random() * objs.length);
            int rand = (int) objs[index];
            System.out.println("随机返回支配者在原数组中的下标：" + rand);

        }
    }

    public static int judge(int[] ints) {
        Arrays.sort(ints);
        int counter = 1;
        for (int i = 0; i < ints.length - 1; i++) {
            if (ints[i] == ints[i + 1]) {
                counter++;
                if (((double) counter) / ints.length > 0.5) {
                    return i;
                }
            } else {
                counter = 1;
            }
        }
        return -1;
    }


    public static int solution(int[] nums) {
        int min = nums[0];
        int maxProfit = 0;

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            if (num - min > maxProfit) {
                maxProfit = num - min;
            }
        }
        return maxProfit;
    }

    /**
     * 斐波那契数列：一列数的规则如下: 1、1、2、3、5、8、13、21、34......
     * 求第30位数是多少， 用递归算法实现
     *
     * @param i
     * @return
     */
    public static int foo(int i) {
        if (i <= 0) {
            return 0;
        } else if (i > 0 && i <= 2) {
            return 1;
        }
        return foo(i - 1) + foo(i - 2);
    }

    /**
     * 数组返转
     *
     * @param array
     * @return
     */
    public static String[] reverseArray(String[] array) {
        String[] newArray = new String[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[array.length - i - 1];
        }
        for (String m : newArray) {
            System.out.println(m);
        }

        return newArray;
    }

    /**
     * 盘点元素在数组中出现的字数
     */
    public static void elementCount() {
        String[] arr = new String[]{"aaa", "bbb", "ccc", "ddd", "ddd", "aaa"};
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (String str : arr) {
            Integer num = map.get(str);
            map.put(str, num == null ? 1 : num + 1);
        }
        for (String mp : map.keySet()) {
            System.out.println("key " + mp + " 出现次数 : " + map.get(mp));
        }
    }

    /**
     * 50个阶梯，一次可以上一阶或是两阶，走上去共有多少种走法？
     *
     * @param s
     * @return
     */
    //方式1，数组叠加
    public static long funJieTi2(int s) {
        long result[] = new long[s + 1];//注意这个要大一个，多了个第0个
        result[0] = result[1] = 1;
        for (int i = 2; i <= s; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[s];//s就是第s+1个
    }

    /**
     * 50个阶梯，一次可以上一阶或是两阶，
     * 走上去共有多少种走法？
     *
     * @param s
     * @return
     */
    //方式2：递归方法实现，非常慢
    public static long funJieTi(int s) {
        if (s == 0 || s == 1) {
            return 1;
        } else {
            return funJieTi(s - 1) + funJieTi(s - 2);
        }
    }

    /**
     * 一个猴子摘了一堆桃子，第一天吃了桃子的一半后又吃了一个，
     * 第二天也吃了剩下的桃子的一半后又吃了一个，以此吃下去，
     * 到了第十天还剩下一个桃子，问当初猴子总摘了多少个桃子？
     * 提示：倒推计算！
     */
    private static void houziMeet() {
        int sum = 0, remain = 1;
        //每天吃剩的桃子加一个正好是前一天桃子的一半，每天桃子的总数就是前一天剩下桃子的数量
        for (int day = 9; day >= 1; day--) {
            sum = (remain + 1) * 2;
            remain = sum;
            System.out.println("第" + day + "天还剩" + remain + "个桃子");
        }
        //1534
        System.out.println(sum);
    }

    /**
     * 1+2+3+4+5+……+100
     * 递归方式实现
     *
     * @param number
     * @return
     */
    public static int calculateSum(int number) {
        int result = 0;
        if (number == 1) {
            result = 1;
        } else {
            result = number + calculateSum(number - 1);
        }
        return result;
    }


    /**
     * java计算运算式1+1*2+1*2*3+1*2*3*4+……+1*2*3*4*……*20
     * 的结果并输出.
     */
    private static void camlJieChen01() {
        int i, j, sum = 0, t = 0;
        for (i = 1; i <= 20; i++) {
            t = 1;
            for (j = 1; j <= i; j++) {
                t *= j;
            }
            sum += t;
        }
        System.out.println(sum);
    }

    /**
     * Java编程题使用for循环计算1-2+3-4+...-100
     */
    private static void caml100() {
        int sum = 1;
        for (int i = 2; i <= 100; i++) {
            if (i % 2 != 0) {
                sum += i;
            } else {
                sum -= i;
            }
        }
        System.out.println(sum);
    }

    /**
     * 计算运算式1+1*2+1*2*3+1*2*3*4+……+1*2*3*4*……*20
     * 的结果并输出.
     */
    public static void jieChen20() {
        long sum = 1;
        long a = 1;
        for (int i = 2; i <= 20; i++) {
            a *= i;
            sum += a;
        }
        System.out.println(sum);
    }

    /**
     * 计算 1/1+1/2+1/3+...+1/100 的值
     *
     * @param m
     * @return
     */
    public static void getSum(int m) {
        double sum = 0;
        for (int i = 1; i <= m; i++) {
            sum += 1.0 / i;
        }
        System.out.println(sum);
        //  return sum;
    }

    /**
     * 3个白球 3个红球 6个黑球 随机拿出8个球，算出所有结果
     */
    private static void camlBall8() {
        int a = 3, b = 3, c = 6, i = 0;
        for (int x = 0; x <= a; x++) {
            for (int y = 0; y <= b; y++) {
                for (int z = 0; z <= c; z++) {
                    if (x + y + z == 8) {
                        System.out.println("红球 " + x + "\t白球 " + y + "\t黑球 " + z);
                        i++;
                    }
                }
            }
        }
        System.out.println("有" + i + "种结果");
    }

    /**
     * 写一个Java程序,计算并输出算式1-1/2+1/3-1/4+.......+1/99-1/100 的结果
     */
    private static void camlFenfu() {
        double a = 1.0;
        // 接收最后结果
        double sum = 0.0;
        // 循环 i的值从1-100
        for (int i = 1; i <= 100; i++) {
            // 观察算式，分母是偶数时，分数为负
            if (i % 2 == 0) {
                sum += -(a / i);
            } else {
                sum += (a / i);
            }
        }
        System.out.println(sum);
    }


    /**
     * 数字金字塔
     */
    private static void numBaoTa() {
        for (int i = 1; i <= 32; i = i * 2) {
            for (int k = 1; k <= 32 / i; k = k * 2) {
                System.out.print("\t");
            }
            for (int j = 1; j <= i; j = j * 2) {
                System.out.print("\t" + j);
            }
            for (int m = i / 2; m >= 1; m = m / 2) {
                System.out.print("\t" + m);
            }
            System.out.print("\n");
        }
    }


    /**
     * 1+2/3+3/5+4/7+5/9…的前20项的和
     */
    public static void caml20() {
        double sum = 0;
        for (int i = 1; i <= 20; i++) {
            sum = sum + i / (2.0 * i - 1);
        }
        System.out.println(sum);
    }

    /**
     * 13、数组中二二组合 为一个指定的数
     * 并找出组合数在原数组中的下标
     *
     * @param result
     */
    public static void getSumGroupResult(int result) {
        int[] arr = new int[]{3, 4, 7};
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
