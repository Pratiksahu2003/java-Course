public class enumc {
    enum Week{
        MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

        Week(int days) {
            this.days = days;
        }
        private int days;
    }

    public static void main(String[] args) {
        Week w = Week.MONDAY;
        System.out.println(w);
        for(Week d : Week.values()){
            System.out.println(d + " " + d.ordinal());
        }
    }
}
