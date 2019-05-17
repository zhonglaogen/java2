package annnotation;

import java.lang.reflect.Method;

public class UserFactory {

    public static User creat(){
        User u1=new User();
        Method[] ms=User.class.getMethods();
       try {
           for(Method method:ms){
               // 如果此方法有注解，就把注解里面的数据赋值到user对象
               if(method.isAnnotationPresent(Init.class)){
                   Init init=method.getAnnotation(Init.class);
                   //执行有注解的方法
                   method.invoke(u1,init.value());
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return u1;
    }

    public static void main(String[] args) {
        User creat = UserFactory.creat();
        System.out.println(creat.getName());
    }
}
