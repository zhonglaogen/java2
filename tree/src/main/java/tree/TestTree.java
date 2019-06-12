package tree;

public class TestTree {
    public static void main(String[] args) {
        int [] arry={1,2,3,4,5,6,7};
        //测试二叉排序树
        int [] arry1={7,3,10,12,5,1,9};
        TwoTree myTree=new TwoTree();
        for (int i = 0; i < arry1.length; i++) {
            myTree.createSortTree(new Node(arry1[i]));
        }
//        myTree.createTree(arry);
        myTree.myPrint();

    }
}
