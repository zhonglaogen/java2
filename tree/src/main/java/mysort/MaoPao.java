package mysort;

/**
 * 冒泡排序 O（n）
 */
public class MaoPao {

    public static int[] sort(int[] arry,int n){
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-i; j++) {
                if(arry[j]<arry[j-1]){
                    int temp=arry[j];
                    arry[j]=arry[j-1];
                    arry[j-1]=temp;
                }
            }
        }
        return arry;
    }

    public static void main(String[] args) {
        int[] arry=new int[]{1,3,3100,321,53,131,5,13,54};
        int[] sort = sort(arry, arry.length);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i]+"\t");
        }

    }
}
