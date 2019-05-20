package compiler;

import java.util.ArrayList;
import java.util.List;

public class MyCompiler {
    class Tree{
        Tree leftTree;
        Tree rightTree;
        String data;
    }
    public MyCompiler(){

    }
    public int compile(String str){
        Tree mytree= creatTree(str);
        int count = midTraversal(mytree);
        return count;
    }
    Tree tree;
    private Tree creatTree(String str) {
        List<String> nums=new ArrayList<>();
        List<String> operators=new ArrayList<>();
        //操作数下标
        int numindex=0;
        //操作符下标
        int opeindex=0;
        //固定区分位；
        final byte sign=0;
        //变动区分位，值为1代表上次是操作符，值为0代表上次是操作数
        byte changeSign=0;
        String numdata="";
        String opedata="";

        byte[] bytes = str.getBytes();
        for(int i=0;i<bytes.length;i++){
            //再此区间为数字
            if (47<bytes[i]&&bytes[i]<58){

                //判断字符位是否结束
                if(sign!=changeSign){
                    nums.add(numdata);
                    numdata="";
                }
                numdata+=String.valueOf((char)bytes[i]);
                changeSign=0;
            }else {

                //判断数字位是否结束
                if(sign==changeSign){
                    //第一次切换到操作符时操作符为空
                    if(!opedata.equals("")){
                        nums.add(opedata);
                    }
                    opedata="";
                    //结束字符位
                }
                opedata+=String.valueOf((char)bytes[i]);
                changeSign=1;
            }
        }
        //因为添加操作是下一次的拼接开始前在添加，最后一次拼接串就不能加到listzhong，需要手动加载
        nums.add(opedata);
        nums.add(numdata);
        System.out.println(nums);
        System.out.println(operators);


        return null;
    }

//    private Tree digui(List<String> nums){
//        if(nums.contains("add") || nums.contains("sub")){
//            for(String myoper:nums){
//                if(myoper.equals("add") || myoper.equals("sub")){
//                    tree.data=myoper;
//                    tree.leftTree=digui();
//                    tree.rightTree=digui();
//                }
//            }
//        }else if(nums.contains("mul") || nums.contains("div")){
//            for(String myoper:nums){
//                tree.data=myoper;
//                tree.leftTree=digui();
//                tree.rightTree=digui();
//            }
//        }else {
//
//        }
//
//        return tree;
//
//    }

    private int midTraversal(Tree mytree) {


        return 0;
    }
}
