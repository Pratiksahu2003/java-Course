import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class c {
   public static void main(String[] args) {
    Collection<String> c = new ArrayList<String>();
    c.add("apple");
    c.add("banana");
    c.add("cherry");
    c.add("date");
    c.add("elderberry");
    c.add("fig");
    c.add("grape");
    c.add("lemon");
    c.add("lime");

    System.out.println(c.size());

    Map<String, Object> map = new HashMap<>();
    map.put("id", 1);
    map.put("name", "Pratik");
    map.put("age", 20);
    map.put("city", "Mumbai");
    map.put("country", "India");
    map.put("email", "pratik@gmail.com");
    map.put("phone", "1234567890");
    map.put("address", "1234567890");
    map.put("zip", "123456");

    System.out.println(map);
   }
}
