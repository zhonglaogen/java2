package tree;

import javax.xml.bind.annotation.XmlAnyAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 普通情况array数组：：：query O(n),update(1)
 * 另一个数组用来求和，数组位置的数字是前几个位置的和：：query是O（1） update是O（n）
 * 线段树，在一个数组中查询某几个数的和，在数组中更改某个位置的值query是O（logn）update是O（logn）
 * 主要是用数组来体现树的结构
 */
public class SegTree {
    /**
     *
     * @param arr 传入的几个数字
     * @param tree 构建的线段树（数组）
     * @param node 当前节点
     * @param start 开始位置
     * @param end 结束位置
     */
    static void buildTree(int arr[], int[] tree, int node, int start, int end) {
        //先放入叶子节点，当插入的是叶子节点停止递归，一定最后会mid等于end或start
        if (start == end) {
            tree[node] = arr[start];
        } else {
            //arry的下标，把arry从中间分成两份，直到分到start==end
            int mid = (start + end) / 2;
            //树（数组）的下标
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;

            buildTree(arr, tree, leftNode, start, mid);
            buildTree(arr, tree, rightNode, mid + 1, end);
            //将节点的和相加
            tree[node] = tree[leftNode] + tree[rightNode];
        }

    }

    /**
     * 更改某个位置的值
     *
     * @param arry
     * @param tree
     * @param node
     * @param start
     * @param end
     * @param index 把tree【index】=val
     * @param val
     */
    static void updateTree(int[] arry, int[] tree, int node, int start, int end, int index, int val) {
        //选择要改的哪个分支
        if (start == end) {
            arry[index] = val;
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            //寻找index的位置
            if (index >= start && index <= mid) {
                updateTree(arry, tree, leftNode, start, mid, index, val);
            } else {
                updateTree(arry, tree, rightNode, mid + 1, end, index, val);
            }
            tree[node] = tree[leftNode] + tree[rightNode];
        }

    }

    /**
     * 选取某一段的和
     *
     * @param arr
     * @param tree
     * @param node
     * @param start
     * @param end
     * @param L 查找的左点
     * @param R 查找的右点
     * @return
     */
    static int query(int[] arr, int[] tree, int node, int start, int end, int L, int R) {
        System.out.println("start =" + start);
        System.out.println("end =" + end);
        System.out.println();
        //寻找到的节点不在范围内，结束递归
        if (start > R || end < L) {
            return 0;
        } else if (start >= L && end <= R) {
            //start和end在L和R的范围内
            return tree[node];
        } else if (start == end) {
            //找到的是节点
            return tree[node];
        } else {
            int mid = (start + end) / 2;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            int sumLeft = query(arr, tree, leftNode, start, mid, L, R);
            int sumRight = query(arr, tree, rightNode, mid + 1, end, L, R);
            return sumLeft + sumRight;
        }


    }

    public static void printTree(int[] tree, int count){
        int index = 0;
        int height = (int) (Math.log(count)/Math.log(2) + 1);
        int rowLength = (int) Math.pow(2, height - 1);
        int gap = rowLength / 2;

//        是否换行

        int markIncre = 1;
        int mark = (int) Math.pow(2,1) - 1;
        int ppCount = 0;
        boolean firstNode = true;
//        首元素之前的空格，每次向下循环都要间隙-2
        int gapSpcae = height;
        StringBuilder gapSpaces = new StringBuilder("          ");




        for (int i = 0; i < count; i++) {
            StringBuilder gaps = new StringBuilder();
            for (int j = 0; j < gap; j++) {
                gaps.append("  ");
            }
            if (firstNode){
                System.out.print(gaps.toString()  + changeString(tree[i]) + gapSpaces.toString());
                firstNode = false;
            }else {
                System.out.print(changeString(tree[i]) + gapSpaces.toString() + "");
            }


            ppCount++;
            if (mark == ppCount){
//                换行操作
                System.out.println();

//                用来标记空位（行首）
                gap--;

//                用来标记换行
                markIncre++;
                mark = (int) Math.pow(2,markIncre) - 1;
                firstNode = true;

//                美化行首空位
                gapSpaces.delete(0,4);

            }
//            遇到换行在减少间隙

        }


    }


    /**
     * 得出数字的位数转换成相应的字符串
     * @param
     * @return
     */
    private static String changeString(int num) {
        num = num>0?num:-num;
        int count =  String.valueOf(num).length();
        switch (count){
            case 1:
                return num + "  ";
            case 2:
                return  num + " ";
            default:
                    return "" + num;
        }
    }





    public static void main(String[] args) {
        int[] arry = {1, 3, 5, 7, 9, 11};
        int size = arry.length;
        int[] tree = new int[50];
        System.out.println("+++++"+tree[16]);
        buildTree(arry, tree, 0, 0, size - 1);
        for (int i = 0; i < 15; i++) {
            System.out.print(tree[i] + "\t");
        }
        System.out.println();
//        printTree(tree,15);
        System.out.println();


        System.out.println();
        updateTree(arry, tree, 0, 0, size - 1, 4, 6);
        for (int i = 0; i < 15; i++) {
            System.out.print(tree[i] + "\t");
        }
        System.out.println();
        int sum = query(arry, tree, 0, 0, size - 1, 2, 5);
        System.out.println(sum);
    }

}
