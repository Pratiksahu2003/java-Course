class str {
    public static void main(String[] args) {
        String name = "maaml";
        StringBuffer sb = new StringBuffer(name);
        sb.reverse();
        System.out.println(sb);
        if (name.equals(sb.toString())) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }
}