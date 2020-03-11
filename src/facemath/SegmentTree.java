package facemath;

/**
 * @Description: 线段树
 * @author: zhonglianxi
 * @date: 2020-03-01
 */
public class SegmentTree {


    private static void buildTree(int[] arry, int[] tree, int curNode, int start, int end) {

        if (start == end) {
//            treenode存的比arry数据多，多存了求和的节点
            tree[curNode] = arry[start];
        } else {
            int leftNode = curNode * 2 + 1;
            int rightNode = curNode * 2 + 2;

            int mid = (start + end) / 2;

            buildTree(arry, tree, leftNode, start, mid);
            buildTree(arry, tree, rightNode, mid + 1, end);

            tree[curNode] = tree[leftNode] + tree[rightNode];
        }

    }




    private static void updateTree(int[] arry, int[] tree, int curNode,
                                   int start, int end, int index, int value) {

        if (start == end){
            arry[index] = value;
            tree[curNode] = value;
        }else {
            int mid = (start + end) / 2;
            int leftNode = curNode * 2 + 1;
            int rightNode = curNode * 2 + 2;

            if (index >= start && index <= mid) {
                updateTree(arry, tree, leftNode, start, mid, index, value);
            } else {
                updateTree(arry, tree, rightNode, mid + 1, end, index, value);
            }

            tree[curNode] = tree[leftNode] + tree[rightNode];
        }




    }


    private static int query(int[] arry, int[] tree, int curNode, int start, int end, int left, int right) {

        if (start > right || end < left){
            return 0;
        }else if (start >= left && end <= right){
            return tree[curNode];
        }else if (start == end){
            return tree[curNode];
        }else {
            int mid = (start + end) / 2;
            int leftNode = curNode * 2 + 1;
            int rightNode = curNode * 2 + 2;

            int leftSum = query(arry, tree, leftNode, start, mid, left, right);
            int rightSum = query(arry, tree, rightNode, mid + 1, end, left, right);
            return leftSum + rightSum;

        }

    }




    public static void main(String[] args) {
        int[] arry = {1, 3, 5, 7, 9, 11};
        for (int i = 0; i < arry.length; i++) {
            System.out.print(arry[i] + "\t");
        }
        System.out.println();
        int size = arry.length;
        int[] tree = new int[50];
        buildTree(arry, tree, 0, 0, size - 1);
        for (int i = 0; i < 20; i++) {
            System.out.print(tree[i] + "\t");
        }
        System.out.println();
//        printTree(tree,13);
        updateTree(arry, tree, 0, 0, size - 1, 4, 6);
        for (int i = 0; i < 15; i++) {
            System.out.print(tree[i] + "\t");
        }
        System.out.println();
//        printTree(tree,13);
        int sum = query(arry, tree, 0, 0, size - 1, 2, 5);
        System.out.println(sum);
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


}
