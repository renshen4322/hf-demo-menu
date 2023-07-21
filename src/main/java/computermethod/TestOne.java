package computermethod;

public class TestOne {
    public static void main(String[] args) {
       // 直接插入排序();
        // 两个待合并数组
        int array1[] = { 20, 10, 50, 40, 30 };
        int array2[] = { 1, 2, 3 };

        // 动态初始化数组，设置数组的长度是array1和array2长度之和
        int array[] = new int[array1.length + array2.length];

        // 循环添加数组内容
        for (int i = 0; i < array.length; i++) {
            if (i < array1.length) {
                array[i] = array1[i];
            } else {
                array[i] = array2[i - array1.length];
            }
        }
        System.out.println("排序之前：");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        for (int i = 1; i < array.length; i++) {
            //待插入元素
            int temp = array[i];
            int j;
            for (j = i-1; j>=0; j--) {
                //将大于temp的往后移动一位
                if(array[j]>temp){
                    array[j+1] = array[j];
                }else{
                    break;
                }
            }
            array[j+1] = temp;
        }

        System.out.println("\n合并后:");
        for (int element : array) {
            System.out.printf("%d ", element);
        }

    }

    private static void 直接插入排序() {
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        //直接插入排序
        for (int i = 1; i < a.length; i++) {
            //待插入元素
            int temp = a[i];
            int j;           
            for (j = i-1; j>=0; j--) {
                //将大于temp的往后移动一位
                if(a[j]>temp){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = temp;
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}
