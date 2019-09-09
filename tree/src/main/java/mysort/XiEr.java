package mysort;

/**o(n^1.3) 坏n^2不稳定
 * 与插入排序相比，减少交换次数，
 * 间隔大的时候移动次数少，
 * 间隔小的时候移动距离短
 */
public class XiEr {

    /**
     * 最后一定要对间隔为一的数组进行插入排序
     * 自定义的间隔，数组很长间隔为4依然很小
     * 普通的插入排序改进的,间隔为gap的插入
     * 每次循环gap+1，与前面的间隔gap插入比较
     * @param arry
     * @param n
     * @return
     */
    public static int[] sort(int[] arry, int n) {

        for (int gap = 4; gap >0 ; gap=gap/2) {
            for (int i = gap; i < n; i++) {
                for (int j = i; j >gap-1; j-=gap) {
                    if (arry[j] < arry[j - gap]) {
                        swap(arry,j,j-gap);
                    } else {
                        break;
                    }
                }
            }
        }

        return arry;
    }

    /**
     * 除以2的间隔
     * @param arry
     * @param n
     * @return
     */
    public static int[] sort2(int[] arry, int n) {

        for (int gap = arry.length >> 1; gap >0 ; gap=gap/2) {
            for (int i = gap; i < n; i++) {
                for (int j = i; j >gap-1; j-=gap) {
                    if (arry[j] < arry[j - gap]) {
                        swap(arry,j,j-gap);
                    } else {
                        break;
                    }
                }
            }
        }

        return arry;
    }

    /**
     * h=1
     * h=3*h+1
     * h大于整个数组长度1/3就没有意义了，超过整个数组长度
     * knuth 序列
     * @param arry
     * @param n
     * @return
     */
    public static int[] sort3(int[] arry, int n) {

        int h=1;
        while (h<=arry.length/3 ){
            h=h*3+1;
        }
        for (int gap = h; gap >0 ; gap=(gap-1)/3) {
            for (int i = gap; i < n; i++) {
                for (int j = i; j >gap-1; j-=gap) {
                    if (arry[j] < arry[j - gap]) {
                        swap(arry,j,j-gap);
                    } else {
                        break;
                    }
                }
            }
        }

        return arry;
    }



    static void swap(int[] tree,int max,int i){
        int temp=tree[max];
        tree[max]=tree[i];
        tree[i]=temp;
    }

    public static void main(String[] args) {
        int[] arry=new int[]{1,3,3100,321,53,131,5,13,54};
        int[] sort = sort2(arry, arry.length);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i]+"\t");
        }

    }
}
