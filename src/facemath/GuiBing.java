package facemath;

/**
 * @Description: 归并排序
 * @author: zhonglianxi
 * @date: 2020-03-01
 */
public class GuiBing {



    private void merage(int[] arry, int start, int mid, int end, int[] copyArray) {
        int i = start;
        int j = mid + 1;
        int m = mid;
        int n = end;
        int index = start;

        while (i <= m && j <= n){
            if (arry[i] < arry[j]){
                copyArray[index++] = arry[i++];
            }else {
                copyArray[index++] = arry[j++];
            }
        }
        while (i <= m){
            copyArray[index++] = arry[i++];
        }
        while (j <= n){
            copyArray[index++] = arry[j++];
        }

//        赋值给原数组，保证接下来的合并都是有序的
        for (int p = start; p <= end; p++){
            arry[p] = copyArray[p];
        }

    }

    public void sort1(int[] arry, int start, int end, int[] copyArray){
        if (start < end){
            int mid = (start + end)/2;
            sort1(arry, start, mid, copyArray);
            sort1(arry, mid + 1, end, copyArray);
            merage(arry,start,mid,end,copyArray);
        }
    }

    public static void main(String[] args) {
        GuiBing q = new GuiBing();
        int[] arry = new int[]{3, 1, 3100, 321, 53, 131, 5, 13, 54};
        int[] arry1 = new int[]{6, 1, 2, 7, 9,3};
        int[] copy = new int[arry.length];
        q.sort1(arry,0,arry.length -1,copy);
        for (int i = 0; i < arry.length; i++) {
            System.out.print(arry[i] + "\t");
        }


    }
}
