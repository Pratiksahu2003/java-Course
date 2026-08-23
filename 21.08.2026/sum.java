import java.util.Scanner;
public class sum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a first number: ");
        int number = scanner.nextInt();
        System.out.println("Enter a second number: ");
        int num2 = scanner.nextInt();
        System.out.println("The number is: " + ( number + num2));
        scanner.close();
    }
}
