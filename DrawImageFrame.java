package compro2.ex;

import javax.swing.*;
import java.awt.*;

public class DrawImageFrame extends JFrame {
    Car car;
    public DrawImageFrame() {
        car = new Car();
        setSize(500, 200);
        add(new MyPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            car.draw(g);
        }
    }
    public static void main(String[] args) {
        DrawImageFrame f = new DrawImageFrame();
    }
}

class Car {
    int x=0, y=0;
    Image image;
    public Car() {
        super();
        ImageIcon icon = new ImageIcon("car.png");
        image = icon.getImage(); // 이미지 아이콘 객체에서 이미지를 추출
    }
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}