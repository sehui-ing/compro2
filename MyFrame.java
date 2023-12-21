package compro2.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//public class Test {
//    public static void main(String[] args) {
//        JFrame f = new JFrame("Frame Test");
//        f.setTitle(" MyFrame ");
//        f.setSize(1000, 700);
//        f.setLocation(1500, 200);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//}

public class MyFrame extends JFrame {
    int x, y;
    class MyPanel extends JPanel {
        public MyPanel() {
            addMouseListener(new MouseAdapter() { //패널에 리스너 등록
                public void mousePressed(MouseEvent e) {
                    x = e.getX();
                    y = e.getY();
                    repaint();
                }
            });
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //주석 처리 후 실행
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, 100, 100);
        }
    }

    public MyFrame() {
        setTitle("Basic Painting");
        setSize(600, 600);
        add(new MyPanel());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }
}
