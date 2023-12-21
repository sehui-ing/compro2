package compro2.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBackgroundTest extends JFrame {
    private JButton button1, button2;
    private JPanel panel;

    MyListener listener = new MyListener();

    private class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                panel.setBackground(Color.yellow);
            } else if (e.getSource() == button2) {
                panel.setBackground(Color.pink);
            }
        }
    }

    public ChangeBackgroundTest() {
        this.setSize(300, 200);
        this.setTitle(" 이벤트 예제 ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        button1 = new JButton(" 노란색 ");
        button1.addActionListener(listener);
        panel.add(button1);

        button2 = new JButton(" 분홍색 ");
        button1.addActionListener(listener);
        panel.add(button2);

        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ChangeBackgroundTest t = new ChangeBackgroundTest();
    }
}
