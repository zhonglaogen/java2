package suanfa;

/**
 * 全排序
 */
public class AllSort {

    void perm(int[] arry,int l,int r){

        if(l==r){
            printArry(arry,r+1);
        }else {
            for (int i=l;i<=r;i++){
                swap(arry,l,i);
                perm(arry,l+1,r);
                swap(arry,l,i);
            }

        }

    }

    private void printArry(int[] arry, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(arry[i]+"\t");
        }
        System.out.println();
    }

    public void swap(int[] arry, int l, int i) {
        int temp=arry[l];
        arry[l]=arry[i];
        arry[i]=temp;
    }

    public static void main(String[] args) {
        int[] arry={1,2,3};
        AllSort allSort=new AllSort();
        allSort.perm(arry,0,2);
    }

}
