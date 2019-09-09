package suanfa;

/**
 * {1,5,23,0,432,67,9,0,2,4}将0移动到末尾
 */
public class EndZero {
    /**
     * 方法一：记录0的个数，交换
     *
     * @param arry
     * @param n
     */
    static void endZero(int[] arry, int n) {
        int zero = 0;
        for (int i = 0; i < n; i++) {
            //若果不是0，就移动zero个位置
            if (arry[i] != 0) {
                arry[i - zero] = arry[i];
            } else {
                zero++;
            }
        }
//
//        if (zero!=0){
//            for (int i=n-1;zero>0;i--,zero--){
//                arry[i]=0;
//            }
//        }

        //将最后的0赋值，上面方法也行
        int i = n - zero;
        while (i < n) {
            arry[i] = 0;
            i++;
        }
    }

    /**
     * 方法二 分三段，可改进为快速排序，正月点灯笼，的zero后移，j为支点，如果小于就交换，大于就不变
     * 同方向的快速排序
     *
     * @param arry
     * @param n
     */
    static void endZero2(int[] arry, int n) {
        int j = 0;
        int i = 0;
        for (; i < n; i++) {
            if (arry[i] != 0) {
                int temp = arry[i];
                arry[i] = arry[j];
                arry[j] = temp;
                j++;
            }
        }
    }

    static void quickSort(int[] arry, int L, int R) {
        if(L<R){
            int mid=partion(arry,L,R);
            quickSort(arry,L,mid-1);
            quickSort(arry,mid+1,R);
        }

    }

    /**
     * j的位置始终是最小和最大的临近点
     *
     * @param arry
     * @param L
     * @param R
     */
    static int partion(int[] arry, int L, int R) {
        int pivot = arry[R];
        int i;
        int j = R;
        for (i = L; i < R; i++) {
            if (arry[i] < pivot) {
                int temp = arry[i];
                arry[i] = arry[j];
                arry[j] = temp;
                j++;
            }
        }
        int temp = arry[j];
        arry[j] = arry[i];
        arry[i] = temp;
        return j;
    }

    public static void main(String[] args) {
        int[] arry = {1, 5, 23, 0, 432, 67, 9, 0, 2, 4};
        endZero2(arry, arry.length);
        for (int i = 0; i < arry.length; i++) {
            System.out.println(arry[i]);
        }
    }
}
