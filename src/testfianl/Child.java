package testfianl;

public class Child extends Parent {
    static {
        System.out.println("child is load");
    }

    Filed filed=new Filed("Child");

    public Child() {
        System.out.println("child con is ");
    }
}
