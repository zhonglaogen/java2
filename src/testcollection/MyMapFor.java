package testcollection;

public class MyMapFor {
    public static void main(String[] args) {
        //map遍历的四种方式
//        //@1
//        for(String key:options.keySet()){
//            System.out.println(key+options.get(key));
//        }
//        Iterator<Map.Entry<String, Method>> iterator = options.entrySet().iterator();
//        //@2
//        while (iterator.hasNext()){
//            Map.Entry entry=iterator.next();
//            System.out.println(entry.getKey()+""+entry.getValue());
//        }
//        //@3
//        for(Map.Entry<String,Method> entry:options.entrySet()){
//            System.out.println(entry.getKey()+""+entry.getValue());
//        }
        //@4只能遍历value
//        for(Method m1:options.values()){
//            try {
//                m1.invoke(o,user);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
