package annnotation;


import java.lang.annotation.*;

@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
/**
 * 注解只有一个属性的时候只能是value
 * 使用@interface定义一个注解，
 * 自动继承了java.lang.annotation.Annotation接口，
 * 其中的每一个方法实际上是声明了一个配置参数。
 * 方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。
 * 可以通过default来声明参数的默认值。
 */
public @interface Init {
    /**
     * 第一,只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型；　
     *  第二,参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型
     *  和 String,Enum,Class,annotations等数据类型，以及这一些类型的数组。
     *  第三,如果只有一个参数成员,最好把参数名称设为"value",后加小括号。
     */
    String value() default  "";
}
