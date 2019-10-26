package testarg;

/**
 * @Description: 测试参数什么时候值传递什么时候引用传递
 * @author: zhonglianxi
 * @date: 2019-10-26
 */
public class TestArgument {
    public static void main(String[] args) {
        String s1 = "123abc";
        String s2 = new String("abc456");
        changeStr(s1);
        changeStr(s2);
        System.out.println(s1);
        System.out.println(s2);
//        Short,Integer,Long,Float,Double,Byte,Boolean,Character,都是不可变的

        /*--------------------字符串不会变，内部对象为final（char【】）,简单的赋值只是改变当前栈帧的指针引用对象-------------------------*/




        Person person = new Person(1, new Phone(11, 12, "myPhone"), "zlx");
        changePer(person);
//        System.out.println(person);
        /*--------------------更改引用对象不会更改,只是复制了一个相同指向的指针，给这个指针赋值，但源指针指向内容不变-------------------------*/



        Person person1 = new Person(1, new Phone(11, 12, "myPhone"), "zlx");
        changePer2(person1);
//        System.out.println(person1);
        /*--------------------更改对象的引用对象会变-------------------------*/



        Person person2 = new Person(1, new Phone(11, 12, "myPhone"), "zlx");
        changePer3(person2);
//        System.out.println(person2);
        /*--------------------更改对象的引用字符串会变-------------------------*/



        Person person4 = new Person(1, new Phone(11, 12, "myPhone"), "zlx");
        changePer4(person4);
//        System.out.println(person4);
        /*--------------------更改对象的引用基本类型会变-------------------------*/


        Person person5 = new Person(1, new Phone(11, 12, "myPhone"), "zlx");
        changePer5(person5);
        System.out.println(person5);


    }

    static void changeStr(String str) {
        str= str+"改变";
    }
    static void changePer(Person str) {
        str=new Person(2, new Phone(11, 12, "myPhone"), "zlx");

    }
    static void changePer2(Person str) {
        str.setPhone(new Phone(21, 12, "myPhone"));

    }
    static void changePer3(Person str) {
        str.setName("other");

    }
    static void changePer4(Person str) {
        str.setId(2);

    }

    static void changePer5(Person str) {
        str.id=4;

    }


}

class Person {
    int id;
    Phone phone;
    String name;

    public Person(int id, Phone phone, String name) {
        this.id = id;
        this.phone = phone;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                '}';
    }
}

class Phone {
    int pid;
    int number;
    String pname;

    public Phone(int pid, int number, String pname) {
        this.pid = pid;
        this.number = number;
        this.pname = pname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "pid=" + pid +
                ", number=" + number +
                ", pname='" + pname + '\'' +
                '}';
    }
}