package refect;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class teseDemo {//泛型：创建String的ArrastList的集合

    public  void test2() throws Exception{
        //通过反射调用无参构造函数创建对象
        //第一种获取Class的方法
        Class class1=Class.forName("com.javatest.se.Person");
        Person p1=(Person) class1.newInstance();
        p1.setName("zhong");
        p1.setAge(20);
        System.out.println(""+p1.getName()+"\t"+p1.getAge());

    }
    public  void test3() throws Exception {
        //反射调用有参构造函数创建对象
        //获取第二种Class类的方法
        Class c2=Person.class;
        //调用有参的构造方法
        //传递是有参的构造方法里面的参数类型，类型使用Class形式传递
       Constructor cs1=c2.getConstructor(String.class,int.class);
       //通过有参的构造方法设值，并创建实例
       Person p2= (Person) cs1.newInstance("liu",19);
        System.out.println(p2.toString());

    }
    public void test4() throws Exception {
        //操作属性
        //第三种获取Class类的方法
        Class c3=new Person().getClass();
        //得到name属性
        Field f1=c3.getDeclaredField("name");
        //设置可以操作私有属性
        f1.setAccessible(true);
        Person p3= (Person) c3.newInstance();
        f1.set(p3,"LI");//设置name的值
        System.out.println(f1.get(p3));
    }
    public void test5()throws Exception{
        //操作普通方法
        Class c4=Person.class;
        //得到方法
        Method m1=c4.getDeclaredMethod("setName", String.class);
        Person p4=(Person) c4.newInstance();
        //m1.setAccessible(true); 方法为private无操作权限时调用此方法
        m1.invoke(p4,"wowo");//调用方法
        //m1.invoke(null,"a");调用静态方法时用此方法，无需创建实例
        System.out.println(p4.toString());
    }


}

