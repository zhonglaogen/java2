package facemath;

/**
 * @Description: 快速排序
 * @author: zhonglianxi
 * @date: 2020-03-01
 */
public class QuickSort {



    public void sort1(int[] array, int start, int end){
        if (start < end){
            int seed = quickSort(array, start, end);
            sort1(array,start,seed -1);
            sort1(array,seed + 1,end);
        }

    }

    private int quickSort(int[] array, int start, int end) {
        int value = array[end];

        int i = start;
        int j = start;

        while (i < end){
            if (array[i] < value){
                swap(array,i,j);
                i++;
                j++;
            }else {
                i++;
            }
        }
        swap(array,j,end);

        return j;
    }

    public void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void sort2(int[] array, int start, int end){
        if (start < end){

            int i = start;
            int j = end;
            int value = array[i];
            while (i < j){
                while (array[j] >= value && i < j){
                    j--;
                }
                if (i < j){
                    array[i] = array[j];
                    i++;
                }
                while (array[i] <= value && i < j){
                    i++;
                }
                if (i < j){
                    array[j] = array[i];
                    j--;
                }
            }

            array[i] = value;
            sort2(array,start,i - 1);
            sort2(array,i + 1, end);
        }
    }


    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        int[] arry = new int[]{3, 1, 3100, 321, 53, 131, 5, 13, 54};
        int[] arry1 = new int[]{6, 1, 2, 7, 9,3};
        q.sort2(arry,0,arry.length -1 );
        for (int i = 0; i < arry.length; i++) {
            System.out.print(arry[i] + "\t");
        }
    }
}
