package stutea;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String stuID;
    private List<Teacher> ts=new ArrayList<>();
    public Student(String stuID){
        this.stuID=stuID;
    }
    public void addTeacher(Teacher tea){
        this.ts.add(tea);
    }

    public List<Teacher> getTs() {
        return ts;
    }
}
