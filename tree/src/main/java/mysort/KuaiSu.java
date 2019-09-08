package mysort;

/**
 * 快速排序
 */
public class KuaiSu {
    public static void sort(int[] arry,int n,int start,int end){
        if(start>end){
            return ;
        }
      int i=start;
      int j=end;
      int key=arry[i];
      while (i<j){
          //从右向左找第一个小于key的值
          while (i<j && arry[j]>key){
              j--;
          }
          //把j位置的数放在位置i
          if(i<j){
              arry[i]=arry[j];
              i++;
          }
          //从左向右找第一个大于key的值
          while (i<j && arry[i]<key){
              i++;
          }
          //把i位置的数放在位置j
          if(i<j){
              arry[j]=arry[i];
              j--;
          }
      }
//      此时位置i是无用的数字，把key给位置i
      arry[i]=key;
      sort(arry,arry.length,start,i-1);
      sort(arry,arry.length,i+1,end);

    }

    public static void main(String[] args) {
        int[] arry=new int[]{3,1,3100,321,53,131,5,13,54};
        sort(arry, arry.length,0,arry.length-1);
        for (int i=0;i<arry.length;i++) {
            System.out.print(arry[i]+"\t");
        }
    }
}
