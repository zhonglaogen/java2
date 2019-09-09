package mysort;

/**
 * O(NlogN)
 * 堆排序
 * 平均时间复杂度：O(NlogN)
 * 由于每次重新恢复堆的时间复杂度为O(logN)，共N - 1次重新恢复堆操作，
 * 再加上前面建立堆时N / 2次向下调整，每次调整时间复杂度也为O(logN)。二次操作时间相加还是O(N * logN)。
 * parent=(i-1)/2
 * c1=2i+1
 * c2=2i+2
 */
public class Dui {

    /**
     * 构建堆
     * @param tree 要进行堆化的数组，
     * @param n 数组长度
     * @param i 对哪个节点进行堆化
     */
   static void heapify(int[] tree,int n,int i){

        if(i>=n){
            return;
        }
        int c1=2*i+1;
        int c2=2*i+2;
        int max=i;
        //判断c1没有越界，
        if(c1<n && tree[c1]>tree[max]){
            max=c1;
        }
        if (c2<n && tree[c2]>tree[max]){
            max=c2;
        }
        if(max!=i){
            //比较两个值不相等，交换
            swap(tree,max,i);
            //交换后会破坏下层堆结构，要对交换的下层堆结构进行再次构建

            heapify(tree,n,max);
        }
    }

    /**
     * 交换
     * @param tree
     * @param max
     * @param i
     */
   static void swap(int[] tree,int max,int i){
        int temp=tree[max];
        tree[max]=tree[i];
        tree[i]=temp;
    }

    /**
     * 找到最后一个非叶子节点，就是最后一个节点的父亲节点，parent=（i-1）/2
     * @param tree
     * @param n
     */
    static void bulid_heap(int[] tree,int n){
       int last_node=n-1;
       int parentnode=(last_node-1)/2;
       //从i开始向上构建堆
        for (int i = parentnode; i >=0 ; i--) {
            heapify(tree,n,i);
        }
    }

    static void heap_sort(int[] tree,int n){
        bulid_heap(tree,n);
        for (int i = n-1; i >=0 ; i--) {
            swap(tree,i,0);
            bulid_heap(tree,i);

        }
    }

    public static void main(String[] args) {
        //有序正向构建堆
//            int[] tree={4,10,3,5,1,2};
//            int n=6;
//            heapify(tree,n,0);

        int[] tree={2,5,3,1,10,4};
        int n=6;
//        bulid_heap(tree,6);
        heap_sort(tree,6);
        for (int i = 0; i < 6; i++) {
            System.out.print(tree[i]+"\t");
        }
    }

}
