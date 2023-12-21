package compro2.ex;

import javax.swing.*;

public class ThreadTest {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        t1.start();
        t2.start();

        MyThread7_1 thread7_1 = new MyThread7_1();
        thread7_1.start();
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다");
    }
}

class MyThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println("-");
        }
    }
}

class MyThread2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println("|");
        }
    }
}

class MyThread7_1 extends Thread {
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                sleep(1000);
            }
            catch (Exception e) {}
        }
    }
}