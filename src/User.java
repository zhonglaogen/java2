import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -7020619477594468968L;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("执行finalize");
        Test1.u1=this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
