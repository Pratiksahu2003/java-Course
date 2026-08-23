import Cal.*;

public class cal {
    add addition = new add();
    sub subtraction = new sub();
    multiple multiplication = new multiple();
    divide division = new divide();

    public int add(int a, int b) {
        return addition.add(a, b);
    }

    public int sub(int a, int b) {
        return subtraction.sub(a, b);
    }

    public int multiple(int a, int b) {
        return multiplication.multiple(a, b);
    }

    public int divide(int a, int b) {
        return division.divide(a, b);
    }
}
