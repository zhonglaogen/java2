package stutea;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {
    Map<String, Student> stus = new HashMap<>();
    Map<String, Teacher> teas = new HashMap<>();
    //初始化学生和老师
    public void creat() {
            stus.put("s1", new Student("s1"));
            stus.put("s2", new Student("s2"));
            stus.put("s3", new Student("s3"));
            stus.put("s4", new Student("s4"));
            stus.put("s5", new Student("s5"));
            stus.put("s6", new Student("s6"));
            stus.put("s7", new Student("s7"));

            teas.put("t1", new Teacher("t1", "Chinese"));
            teas.put("t2", new Teacher("t2", "Math"));
            teas.put("t3", new Teacher("t3", "English"));
    }
    //选课
    public void chooseCourse( String sID,String tID){
        Student s1 = stus.get(sID);
        Teacher t2 = teas.get(tID);

        //将学生加到老师的List，将老师加到学生的List里
        if(s1!=null && s1!=null){
            t2.addStudent(s1);
            s1.addTeacher(t2);
        }else{
            System.out.println("学生或老师不存在");
        }

    }

    public static void main(String[] args) {
        Test test=new Test();
        test.creat();
        //输入老师学生的关系
        test.chooseCourse("s1","t1");
        test.chooseCourse("s1","t2");
        test.chooseCourse("s3","t2");
        test.chooseCourse("s3","t3");
        test.chooseCourse("s2","t2");


        while (true){
            System.out.println("请输入要查询的学生Id");
            Scanner sc=new Scanner(System.in);
            String studentId=sc.next();
            Student student = test.stus.get(studentId);
            if(student!=null){
                List<Teacher> ts =student .getTs();
                for(Teacher t1:ts){
                    System.out.println("已选课程为"+t1.getCource());
                }
            }else {
                System.out.println("输入学生ID有误");
            }



        }

    }
}