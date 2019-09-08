public class User2{
    @Override
    public int hashCode() {
        return 9;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return false;
    }
}
