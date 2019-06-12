package tree;

import java.util.ArrayList;
import java.util.List;
 class Node{
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
    public void prePrint(){
        System.out.print(data+"->");
        if(this.left!=null){
            this.left.prePrint();
        }
        if ( this.right!=null){
            this.right.prePrint();
        }
    }
    public void midPrint(){

        if(this.left!=null){
            this.left.midPrint();
        }
        System.out.print(data+"->");
        if ( this.right!=null){
            this.right.midPrint();
        }
    }
    public void latPrint(){

        if(this.left!=null){
            this.left.latPrint();
        }
        if ( this.right!=null){
            this.right.latPrint();
        }
        System.out.print(data+"->");
    }


     //二叉排序树
    public void add(Node node){
        if(node== null){
            return;
        }
        if (node.data<this.data){
            if(this.left==null){
                this.left=node;
            }else {
                this.left.add(node);
            }

        }else {
            if (this.right==null){
                this.right=node;
            }else {
                this.right.add(node);
            }
        }


    }

}
public class TwoTree{

    private List<Node> myNodes;
    private Node root;

    public TwoTree() {
        this.myNodes=new ArrayList();
    }
    //创建二叉排序树
    public void createSortTree(Node node){
        if(root==null){
            root=node;
        }else {
            root.add(node);
        }
    }

    /**创建完全二叉树
     * 叶子节点只能比非叶子节点多一个或相等
     * @param arry
     */
    public void createTree(int[] arry){
        for(int data:arry){
            myNodes.add(new Node(data));
        }
         root=  myNodes.get(0);
        /**
         * length/2（2i+1） 的值要么是整除，要么是余数为一（2i+2）
         */
        for(int i=0;i<arry.length/2;i++){
            myNodes.get(i).left=  myNodes.get(2*i+1);
            if((2*i+2)<arry.length){
                myNodes.get(i).right=myNodes.get(2*i+2);
            }
        }
    }
    //打印二叉树
    public void myPrint() {
        if (root != null) {
            root.prePrint();
        }
        System.out.println();
        if (root != null) {
            root.midPrint();
        }

        System.out.println();
        if (root != null) {
            root.latPrint();
        }

    }
}
