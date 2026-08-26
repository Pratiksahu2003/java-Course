import java.lang.Thread;
class p extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("A");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class q extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("b");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class t {
    public static void main(String[] args) {
        p t1 = new p();
        q t2 = new q();
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}