package compro2.ex;

public class Ex5 {
    public static void main(String[] args) {
        Outer objO = new Outer();
        // Outer.Inner objI = objO.new Inner();
        Outer.Inner objI = new Outer().new Inner();
        // Outer.Inner obji = new Outer.Inner();
        objI.myMethod();
    }
}
class Outer {
    private int value = 10;

    class Inner {
        public void myMethod() {
            System.out.println("Outer의 private 변수 값 : " + value);
        }
    }

    Outer() {
        Inner obj = new Inner();
        obj.myMethod();
    }
}