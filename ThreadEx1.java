package compro2.ex;

public class ThreadEx1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        MyThread t1 = new MyThread();

        Runnable r = new MyRunnable();
        Thread t3 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }
}

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName());
        }
    }
}

class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
