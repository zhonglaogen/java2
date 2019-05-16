package selectdeal;

import com.sun.xml.internal.ws.api.model.MEP;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 根据已经选择的功能进行处理
 */
public class DealOptions {
   private List<Method> options=new ArrayList<>();

    public void addFun(Class myClass,String funName) throws NoSuchMethodException {
        //根据方法名和参数类型找到myClass的方法
        Method method=myClass.getMethod(funName,User.class);
        options.add(method);
    }
    public void invokeMethod(Object o,User user) throws InvocationTargetException, IllegalAccessException {

        for(Method m1:options){
            //根据实例和方法参数调用方法
            m1.invoke(o,user);
        }

    }

}
