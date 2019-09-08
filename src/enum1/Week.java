package enum1;

public enum  Week {
    ONE("a"),TWO("b"),THREE("c");
    private String v;

     Week(String v){
        this.v=v;
    }

    public static void main(String[] args) {
        //返回与此枚举常量的枚举类型相对应的 Class 对象。
        Class<Week> declaringClass = Week.ONE.getDeclaringClass();
        System.out.println(declaringClass);

        //比较此枚举与指定对象的顺序。
        int i = Week.TWO.compareTo(Week.ONE);
        System.out.println(i);

        //返回此枚举常量的名称，在其枚举声明中对其进行声明。
        String name = Week.THREE.name();
        System.out.println(name);

        //返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）。
        int ordinal = Week.ONE.ordinal();
        System.out.println(ordinal);

        String s = Week.ONE.toString();
        System.out.println(s);

        Week a = Week.valueOf("ONE");
        System.out.println(a);

        Week[] values = Week.values();
        System.out.println();


    }
}
