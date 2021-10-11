public class EqualsTest {
    public static void main(String[] args) {
        MyClass c1 = new MyClass();
        MyClass c2 = new MyClass();

        c1.a = 1;
        c1.b = 2;
        c2.a = 1;
        c2.b = 2;
        System.out.println(c1.equals(c2));

    }
}

class MyClass {
    int a;
    int b;
}
