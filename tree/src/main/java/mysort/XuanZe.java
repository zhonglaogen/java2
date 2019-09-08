package mysort;

/**
 * 选择
 */
public class XuanZe {

    public static int[] sort(int[] arry,int n){
        for (int i = 0; i < n; i++) {
            for (int j=1;j<n-i;j++){
                if (arry[i]>arry[i+j]){
                    int tem=arry[i];
                    arry[i]=arry[i+j];
                    arry[i+j]=tem;
                }
            }

        }

        return arry;
    }

    /**
     * 上一种是交换了值，这种只是交换下表
     * @param arry
     * @param n
     * @return
     */
    public static int[] sort1(int[] arry,int n){
        for (int i = 0; i < n-1; i++) {
            int min=i;
            for (int j=i+1;j<n;j++){
                if (arry[min]>arry[j]){
                    min=j;
                }
            }
            if(min!=i){
                int tem=arry[i];
                arry[i]=arry[min];
                arry[min]=tem;
            }

        }

        return arry;
    }


    public static void main(String[] args) {
        int[] arry=new int[]{1,3,3100,321,53,131,5,13,54};
        int[] sort = sort1(arry, arry.length);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i]+"\t");
        }

    }
}
