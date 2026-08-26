interface A{
    int show(int i , int j);
}

public class lam {
    public static void main(String[] args) {
       A a = (i, j) -> i + j;
       System.out.println(a.show(10, 20));
    }
    
}
