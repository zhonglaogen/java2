package mysort;

/**
 * 插入
 */
public class ChaRu {

    public static int[] sort(int[] arry, int n) {
        for (int i = 1; i < n; i++) {
            for (int j = i; j >0; j--) {
                if (arry[j] < arry[j - 1]) {
                    int temp = arry[j];
                    arry[j] = arry[j - 1];
                    arry[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return arry;
    }


    public static void main(String[] args) {
        int[] arry=new int[]{3,1,3100,321,53,131,5,13,54};
        int[] sort = sort(arry, arry.length);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i]+"\t");
        }

    }
}
