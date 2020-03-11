package facemath;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-03-01
 */
public class HeapSort {


    public void sort1(int[] arry, int n) {
        build_heap(arry, n);
        for (int j = n - 1; j > 0; j--) {
            swap(arry, 0, j);
            build_heap(arry, j);
        }
    }

    private void build_heap(int[] arry, int n) {
        int last_node = n - 1;
        int parent_node = (last_node - 1) / 2;
        for (int i = parent_node; i >= 0; i--) {
            heapify(arry, i, n);
        }

    }

    private void heapify(int[] arry, int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left < n && arry[left] > arry[max]) {
            max = left;
        }
        if (right < n && arry[right] > arry[max]) {
            max = right;
        }
        if (max != i) {
            swap(arry, max, i);
            heapify(arry,max,n);
        }
    }

    private void swap(int[] arry, int j, int i) {
        int temp = arry[j];
        arry[j] = arry[i];
        arry[i] = temp;
    }

    public static void main(String[] args) {
        HeapSort q = new HeapSort();
        int[] arry = new int[]{3, 1, 3100, 321, 53, 131, 5, 13, 54};
        int[] arry1 = new int[]{6, 1, 2, 7, 9, 3};
        q.sort1(arry, arry.length);
        for (int i = 0; i < arry.length; i++) {
            System.out.print(arry[i] + "\t");
        }
    }

}
