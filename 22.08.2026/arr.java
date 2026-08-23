public class arr {
    public static void main(String[] args) {
        // Object[] arr = {1, 2, "Hello", 4, 5};
        // for (Object i : arr) {
        //     System.out.println(i);
        // }

     Object[][] student = {
      {1 , "pratik sahu" , "pratik@gmail.com" , "1234567890"},
      {2 , "john doe" , "john@gmail.com" , "1234567890"},
      {3 , "jane doe" , "jane@gmail.com" , "1234567890"},
      {4 , "jim beam" , "jim@gmail.com" , "1234567890"},
      {5 , "jill hill" , "jill@gmail.com" , "1234567890"},
     };
     for (Object[] i : student) {
        for (Object j : i) {
            System.out.println(j);
        }
        System.out.println();
    }

   
        
    }
}
