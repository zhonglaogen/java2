package annnotation;

public class User {
    private String name;
    @Init(value = "zlx")
    public void setName(String name){
        this.name=name;
    }

    public void printHello(){
        System.out.println("hello");
    }

    public String getName() {
        return name;
    }
}
