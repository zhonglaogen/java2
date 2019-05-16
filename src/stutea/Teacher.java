package stutea;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String teaID;
    private String cource;
    private List<Student> ss=new ArrayList<>();
    public Teacher(String teaID,String cource){
        this.cource=cource;
    }
    public void addStudent(Student s){
        ss.add(s);
    }

    public String getCource() {
        return cource;
    }
}
