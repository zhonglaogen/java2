package testfianl;

public class Parent {

    static {
        System.out.println("parent is load");
    }

    Filed filed=new Filed("parent");

    public Parent() {
        System.out.println("parent con ");
    }
}
