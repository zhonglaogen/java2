package niginx;

import java.io.Serializable;

public class Transport implements Serializable {
    private static final long serialVersionUID = 5394047904759350732L;
    private StringBuilder message;
    public Transport(StringBuilder message) {
        this.message = message;
    }
    public StringBuilder getMessage() {
        return message;
    }

    public void setMessage(StringBuilder message) {
        this.message = message;
    }


}
