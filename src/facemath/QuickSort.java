package facemath;

/**
 * @Description: 快速排序
 * @author: zhonglianxi
 * @date: 2020-03-01
 */
public class QuickSort {


    public static void sort1(int[] array, int start, int end) {
        if (start < end) {
            int seed = quickSort(array, start, end);
            sort1(array, start, seed - 1);
            sort1(array, seed + 1, end);
        }

    }

    private static int quickSort(int[] array, int start, int end) {
        int value = array[end];

        int i = start;
        int j = start;

        while (i < end) {
            if (array[i] < value) {
                swap(array, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }
        swap(array, j, end);

        return j;
    }


    public int findKth(int[] arr, int k) {
//        查找第k小的奇数
//        首先过滤得到数组内的所有奇数
//        时间复杂度 数组内不存在第k小的数字时复杂度为O(n);
//          存在k时时间复杂度为O(nlogn),n为奇数的个数
        int i = 0;
        int j = 0;
        int len = arr.length;
        while (i < len) {
            if (arr[i] % 2 == 0) {
                i++;
            } else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }
//        j的位置就是偶数的位置
        if (j == 0 || j < k ){
            return 0;
        }
//        对前j个元素进行排序
        sort1(arr,0,j-1);
        return  arr[k - 1];

    }

    public static void  swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void sort2(int[] array, int start, int end) {
        if (start < end) {

            int i = start;
            int j = end;
            int value = array[i];
            while (i < j) {
                while (array[j] >= value && i < j) {
                    j--;
                }
                if (i < j) {
                    array[i] = array[j];
                    i++;
                }
                while (array[i] <= value && i < j) {
                    i++;
                }
                if (i < j) {
                    array[j] = array[i];
                    j--;
                }
            }

            array[i] = value;
            sort2(array, start, i - 1);
            sort2(array, i + 1, end);
        }
    }


    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        int[] arry = new int[]{3, 1, 3100, 321, 53, 131, 5, 13, 54};
        int[] arry1 = new int[]{6, 1, 2, 7, 9, 3};
//        q.sort2(arry, 0, arry.length - 1);

        System.out.println(q.findKth(arry1, 3));
        for (int i = 0; i < arry1.length; i++) {
            System.out.print(arry1[i] + "\t");
        }
    }



}
