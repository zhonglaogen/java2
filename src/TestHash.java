import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHash {
//   static String aa="aaa";
    static void say(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
//        HashMap<User2, Integer> map=new HashMap<>();
//        List<User2> user2=new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            user2.add(new User2());
//        }
//        for (int i = 0; i < 10; i++) {
//            map.put(user2.get(i),i);
//        }
//        map.remove(user2.get(1));
//        map.remove(user2.get(2));
//        map.remove(user2.get(3));
//
//        float a= (float) 1.4;
//        System.out.println();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
////        executorService.execute();

        StringBuilder s1=new StringBuilder("aaa");
        StringBuilder s12=new StringBuilder("aaa");
        s1.append("bbb");
        s12=s1;
//       final String aa="aaa";
//        aa="bbb";
        System.out.println(s1);
        System.out.println(s12);
//        System.out.println(aa);

        TestHash q=new TestHash();


    }
}

