package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * logn
 */
public class SegTree {
    static void buildTree(int arr[], int[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;

            buildTree(arr, tree, leftNode, start, mid);
            buildTree(arr, tree, rightNode, mid + 1, end);
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
     * @param L
     * @param R
     * @return
     */
    static int query(int[] arr, int[] tree, int node, int start, int end, int L, int R) {
        System.out.println("start =" + start);
        System.out.println("end =" + end);
        System.out.println();
        if (start > R || end < L) {
            return 0;
        } else if (start >= L && end <= R) {
            return tree[node];
        } else if (start == end) {
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

    public static void main(String[] args) {
        int[] arry = {1, 3, 5, 7, 9, 11};
        int size = arry.length;
        int[] tree = new int[50];
        System.out.println(tree[16]);
        buildTree(arry, tree, 0, 0, size - 1);
        for (int i = 0; i < 15; i++) {
            System.out.print(tree[i] + "\t");
        }


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
