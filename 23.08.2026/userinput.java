import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class userinput {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = br.readLine();
            System.out.println(name);
        } catch (IOException e) {
            System.out.println(e);
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
