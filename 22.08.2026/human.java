    class student{
        private String name;
        private int age;
    
        public student(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }

    class human {
        public static void main(String[] args) {
            student s = new student("pratik", 20);
            System.out.println(s.getName());
            System.out.println(s.getAge());
        }
    }