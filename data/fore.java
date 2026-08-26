import java.util.Arrays;
import java.util.List;
public class fore {
    public static void main(String[] args) {
       List<Integer> list = Arrays.asList(3,4,5,6,7,8,9,10);

       list.forEach(number -> {
        if(number % 2 == 0) {
            System.out.println("Even number: " + number);
        } else {
            System.out.println("Odd number: " + number);
        }
       });
    }
}   
