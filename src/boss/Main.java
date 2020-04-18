package boss;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-04-03
 */
public class Main {

    static AtomicInteger dynamic = new AtomicInteger(0);

    static class Task implements Runnable {
        public Task(int k, int n) {
            this.k = k;
            this.n = n;
        }

        int k = 0;
        int n = 0;

        @Override
        public void run() {
            int curId = Integer.valueOf(Thread.currentThread().getName());
            while (true) {
                if (dynamic.get() >= n) {
                    break;
                }
                if ((dynamic.get() % k) == curId) {
                    int cur = dynamic.get();
                    int update = cur + 1;
                    int outvalue = dynamic.get() + 1;
                    System.out.print(curId + 1 + ":" + outvalue + "\t");
                    dynamic.compareAndSet(cur, update);
                }
            }

        }
    }

    public static void threadtest(String[] args) {


        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();


        for (int i = 0; i < k; i++) {
            new Thread(new Task(k, n), String.valueOf(i)).start();
        }
    }

    //    public static void count1(String[] args) {
//        long a = 3;
//        int count = 0;
//        for (int i = 0; i < 64; i++) {
//            if ((a & 1) == 1){
//                count++;
//            }
//            a >>>= 1;
//        }
//        System.out.println(count);
//    }
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    //
    public static ListNode revertLinkList(ListNode root) {
        if (root == null) {
            return root;
        }
        // write code here
        List<ListNode> ls = new ArrayList();
        ls.add(root);
        int count = 1;
        ListNode curNode = root.next;
        while (curNode != null) {
            ls.add(curNode);
            curNode = curNode.next;
            count++;
        }
        root = ls.get(ls.size() - 1);
        curNode = root;
        ListNode indexNode = null;
        if (ls.size() > 1) {
            for (int i = ls.size() - 2; i >= 0; i--) {
                indexNode = ls.get(i);
                curNode.next = indexNode;
            }
        }
        return root;
    }

    public static ListNode revertLinkList2(ListNode root) {
        if (root == null) {
            return root;
        }
        // write code here
        ListNode p1;
        ListNode p2;
        ListNode cur;

        p1 = root;
        cur = root.next;
        while (cur != null) {
            p2 = cur;
            cur = cur.next;
            p2.next = p1;
            p1 = p2;
        }
        root = p1;


        return root;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        revertLinkList2(l1);
    }


//    public static int maxHeight = 0;
//    public static int getTreeHeight (TreeNode root) {
//        // write code here
//        getHeight(root,0);
//        return maxHeight;
//
//    }
//
//    public static int getHeight(ThreeNode node,int count){
//        count++;
//        if (count > maxHeight){
//            maxHeight = count;
//        }
//        getHeight(node.left,count);
//        getHeight(node.right,count);
//        count --;
//        return count;
//    }
}
