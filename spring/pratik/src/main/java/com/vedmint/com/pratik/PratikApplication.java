package com.vedmint.com.pratik;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.vedmint.com.pratik.entity.Human;
import com.vedmint.com.pratik.entity.Man;
@SpringBootApplication
public class PratikApplication {
	

	public static void main(String[] args) {
	ApplicationContext context = SpringApplication.run(PratikApplication.class, args);
    
	 Human human = context.getBean(Human.class);
	 human.move();
	 human.breathe();
	 human.think();
	 human.feel();
	 
	 Man man = context.getBean(Man.class);
	 man.setName("John");
	 man.setAge(20);
	 man.setEmail("john@example.com");
	 man.setPhone("1234567890");
	 man.setAddress("123 Main St, Anytown, USA");
	 System.out.println(man.getName());
	 System.out.println(man.getAge());
	 System.out.println(man.getEmail());
	 System.out.println(man.getPhone());
	 System.out.println(man.getAddress());
	 man.move();
	 man.breathe();
	 man.think();
	 man.feel();
	}
}
