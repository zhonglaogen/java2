package testcollection;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 深克隆和浅克隆
 */
public class TestCopy {
    private final ConcurrentHashMap con;
    private final Map<String,Integer> map;


    public Map<String, Integer> getLowMap() {
        return map;
    }
    public Map<String, Integer> getDeepMap() {
        return Collections.unmodifiableMap(new HashMap<>(map));
    }


    public Map<String, Integer> getConMap() {
        return con;
    }

    public TestCopy(String id,int x){
        con=new ConcurrentHashMap<String,Integer>();
        con.put(id,x);
        map=Collections.unmodifiableMap(con);
    }

    public void setCon(String id,int x){
        if(con.replace(id,x)==null){
            throw new IllegalArgumentException("error"+id);
        }

    }



    public static void main(String[] args) {
        TestCopy t1=new TestCopy("aa",1);
        //测试Collections.unmodifiableMap(con)方法 测试结果更改con的值map的值也改变，浅克隆,深克隆map的值不变
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map1= t1.getLowMap();
                System.out.println(map1.get("aa"));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(map1.get("aa"));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t1.setCon("aa",2);
            }
        }).start();

        //测试外部线程能否更改成员变量内部变量的值,测试结果：成员变量con逸出，外部线程可以随意更改con的值

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                t1.getConMap().replace("aa",22);
//                System.out.println(t1.getConMap().get("aa"));
//
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//                System.out.println(t1.getConMap().get("aa"));
//            }
//        }).start();



        



    }


}
