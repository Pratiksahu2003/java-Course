public class forloop {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

     String name = "John";
     for (int i = 0; i < name.length(); i++) {
        System.out.println(name.charAt(i));
     }  
     int[] numbers = {1, 2, 3, 4, 5};
     for (int number : numbers) {
        System.out.println(number);
     }
    }
}
