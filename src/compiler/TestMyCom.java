package compiler;

public class TestMyCom {
    public static void main(String[] args) {
        String a="1add3mul2sub1add5";//11
//        int b=new MyCompiler().compile(a);
//        System.out.println(b);
        new MyCompiler().compile(a);

    }
}
