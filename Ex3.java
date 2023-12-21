package compro2.ex;

public class Ex3 implements Printable, Drawable {
    public void print() {
        System.out.println("프린터로 원을 출력합니다.");
    }
    public void draw() {
        System.out.println("화면에 원을 그립니다.");
    }
    public static void main(String args[]) {
        Ex3 obj = new Ex3();
        obj.print();
        obj.draw();
    }
}
interface Printable {
    void print();
}

interface Drawable {
    void draw();
}