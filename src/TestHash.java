import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHash {
//   static String aa="aaa";
static class User{
        public int id;

    public User(int id) {
        this.id = id;
    }
}

    static void say(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
        Integer integer = new Integer(9999);
        change(integer);
        String b="bbbb";
        String a=new String("1111");
        StringBuilder stringBuilder=new StringBuilder("aaa");
        User u=new User(1);
        int[] arry={1,2,3,4};
        change(b);
        System.out.println(b);


    }

    private static void change(int[] arry) {
        arry=new int[]{2,2,2,2};
    }

    private static void change(User u) {
       u.id=10;
    }

    private static void change(StringBuilder stringBuilder) {
        stringBuilder=new StringBuilder("ssss");
    }

    private static void change(String a) {
        a=a+"s";
    }

    private static void change(Integer integer) {
        integer=integer+1;
    }
}

