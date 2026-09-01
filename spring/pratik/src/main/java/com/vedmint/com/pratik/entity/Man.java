package com.vedmint.com.pratik.entity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.vedmint.com.pratik.Interface.Body;

@Component
public class Man {
    private final Body body;

    public Man(Body body) {
        this.body = body;
    }

    @Value("${man.name}")
    private String name;
    @Value("${man.age}")
    private int age;
    @Value("${man.email}")
    private String email;
    @Value("${man.phone}")
    private String phone;
    @Value("${man.address}")
    private String address;

   public void SetMan(String name, int age, String email, String phone, String address) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.phone = phone;
    this.address = address;
   }
   public String getName() {
    return name;
   }
   public int getAge() {
    return age;
   }
   public String getEmail() {
    return email;
   }
   public String getPhone() {
    return phone;
   }
   public String getAddress() {
    return address;
   }
   public void setName(String name) {
    this.name = name;
   }
   public void setAge(int age) {
    this.age = age;
   }
   public void setEmail(String email) {
    this.email = email;
   }
   public void setPhone(String phone) {
    this.phone = phone;
   }
   public void setAddress(String address) {
    this.address = address;
   }
    
   public void move() {
    System.out.println("Man is moving");
   }
   public void breathe() {
    System.out.println("Man is breathing");
   }
   public void think() {
    System.out.println("Man is thinking");
   }
   public void feel() {
    System.out.println("Man is feeling");
   }
}
