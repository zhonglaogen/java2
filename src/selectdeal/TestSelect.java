package selectdeal;

import java.lang.reflect.InvocationTargetException;

public class TestSelect {
   static OptinonFun optinonFun=new OptinonFun();
    public static void main(String[] args) {
        User u1=new User("zlx");
        DealOptions deal=new DealOptions();
        String[] methods={"add1","add3","add2","add1"};
        for(int i=0;i<methods.length;i++){
            try {
                deal.addFun(OptinonFun.class,methods[i]);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        try {
            deal.invokeMethod(optinonFun,u1);
            //无法找到类目标异常，没有权限访问异常
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(u1.name);


    }

}
