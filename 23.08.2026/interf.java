interface A{
    void show();
}

interface B{
    void notShow();
}

class C implements A, B{
    public void show(){
        System.out.println("Hello");
    }
    public void notShow(){
        System.out.println("Hello");
    }
}

public class interf {
    public static void main(String[] args) {
        C c = new C();
        c.show();
        c.notShow();
    }
}