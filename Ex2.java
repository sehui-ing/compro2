package compro2.ex;

public class Ex2 {
    public static void main(String[] args) {
        Shape arrayOfShapes[] = new Shape[3];
        arrayOfShapes[0] = new Rectangle();
        arrayOfShapes[1] = new Triangle();
        arrayOfShapes[2] = new Circle();

        for (int i = 0; i < arrayOfShapes.length; i++) {
            arrayOfShapes[i].draw();
        }
    }
}
class Shape {
    public void draw() {
        System.out.println("Shape Draw");
    }
}
class Rectangle extends Shape {
    private int width, height;
    public void draw() {
        super.draw();
        System.out.println("Rectangle Draw");
    }
}

class Triangle extends Shape {
    private int base, height;
    public void draw() {
        System.out.println("Triangle Draw");
    }
}

class Circle extends Shape {
    private int radius;
    public void draw() {
        System.out.println("Circle Draw");
    }
}
