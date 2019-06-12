package tree;
public class AVLTree {
    private AVLNode root;
    //创建二叉排序树
    public void createSortTree(AVLNode node){
        if(root==null){
            root=node;
        }else {
            root.add(node);
        }
    }
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

    public static void main(String[] args) {
        AVLTree a=new AVLTree();
        //测试avltree左旋
//        int[] arry2={4,3,6,5,7,8};
//        int[] arry2={10,12,8,9,7,6};
        int [] arry2={10,11,7,6,8,9};
        for(int i=0;i<arry2.length;i++){
            a.createSortTree(new AVLNode(arry2[i]));
        }
        a.myPrint();
        System.out.println("");
        System.out.println("no spin");
        System.out.println("树的高度="+a.root.height());

        System.out.println("左子树的高度="+a.root.leftHeight());

        System.out.println("右子树的高度="+a.root.rightHeight());


    }
}
class AVLNode{
    int data;
    AVLNode left;
    AVLNode right;

    public AVLNode(int data) {
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
    public void add(AVLNode node){
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

        //当添加完一个节点后右子树高度-左子树的高度大于1
        if(rightHeight()-leftHeight()>1){
            //如果右子树的左子树高度大于它的右子树高度
            //先对它右子树进行右旋转，在对当前节点左旋转

            //要判断右子树是否为空，如果为空会旋转报错（不写也行，只要是高度大于1的都不为空）
            if(right!=null && right.leftHeight()>right.rightHeight()){
                right.rightSpin();
                leftSpin();
            }else {
                leftSpin();
            }
            return;

        }
        //当添加完一个节点后左子树高度-右子树的高度大于1
        if(leftHeight()-rightHeight()>1){
            if(this.left.rightHeight()>this.left.leftHeight()){
                left.leftSpin();
                //在对当前节点进行右旋转
                rightSpin();
            }else {
                rightSpin();
            }

        }
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left==null){
            return 0;
        }else {
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight(){
        if (right==null){
            return 0;
        }else {
            return right.height();
        }
    }

    //返回当前节点的高度，以该节点为根节点的高度
    public int height(){
        return Math.max(left == null ? 0:left.height(),right == null ? 0:right.height())+1;
    }

    //左旋转
    private void leftSpin(){
        //创建新的节点
        AVLNode newNode = new AVLNode(data);
        //把新的节点左子树设置成当前节点的左子树
        newNode.left=this.left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right=this.right.left;
        //把当前节点的值变成右子树的值
        this.data=this.right.data;
        //把当前节点的右子树设置成当前节点的右子树的右子树
        this.right=this.right.right;
        //把当前节点的左子树设置成新节点
        this.left=newNode;
    }

    //右旋转
    private void rightSpin(){
        AVLNode newNodea=new AVLNode(data);
        newNodea.right=this.right;
        newNodea.left=this.left.right;
        this.data=this.left.data;
        this.left=this.left.left;
        this.right=newNodea;
    }

}
