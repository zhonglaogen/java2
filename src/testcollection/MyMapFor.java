package testcollection;

public class MyMapFor {
    public static void main(String[] args) {
        //map遍历的四种方式
//        //@1第一种：普遍使用，二次取值
//        for(String key:options.keySet()){
//            System.out.println(key+options.get(key));
//        }
//        Iterator<Map.Entry<String, Method>> iterator = options.entrySet().iterator();
//        //@2通过Map.entrySet使用iterator遍历key和value
//        while (iterator.hasNext()){
//            Map.Entry entry=iterator.next();
//            System.out.println(entry.getKey()+""+entry.getValue());
//        }
//        //@3推荐，尤其是容量大时
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
