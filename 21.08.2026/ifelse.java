import java.util.Scanner;
class ifelse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        if (age >= 18)
            System.out.println("You are an adult");
        else if (age >= 13) {
            System.out.println("You are a teenager");
        } else if (age >= 10) {
            System.out.println("You are a child");
        } else if (age >= 3) {
            System.out.println("You are a baby");
        } else if (age >= 1) {
            System.out.println("You are a toddler");
        } else if (age >= 0) {
            System.out.println("You are a newborn");
        } else {
            System.out.println("You are not a human");
        }
    }
}
