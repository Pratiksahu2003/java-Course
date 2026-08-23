public class animal{
    static  String name;
    static {
        name = "Animal";
    }
    public void eat(){
        System.out.println("Animal is eating");
    }
    public void sleep(){
        System.out.println("Animal is sleeping");
    }
    public void makeSound(){
        System.out.println("Animal is making sound");
    }

    public static void main(String[] args) {
        dog d = new dog();
        d.bark();
        cat c = new cat();
        c.meow();
        animal a = new animal();
        a.eat();
        a.sleep();
        a.makeSound();
    }
}

class dog extends animal{
    public void bark(){
        System.out.println("Dog is barking");
    }
}
class cat extends animal{
    public void meow(){
        System.out.println("Cat is meowing");
    }
}

