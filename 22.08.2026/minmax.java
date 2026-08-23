import java.util.Arrays;

public class minmax {
    public static void main(String[] args) {
        int[] numbers = {45, 22, 89, 16, 90, 33};
        Arrays.sort(numbers);
        System.out.println("Smallest:" + numbers[0] + "Largest:" + numbers[numbers.length-1]);
    }
}
