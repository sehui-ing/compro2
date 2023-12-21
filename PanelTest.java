package compro2.ex;
import java.awt.*;
import javax.swing.*;
public class PanelTest extends JFrame {
    public PanelTest() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.orange);

        JButton b1 = new JButton(" Button1 ");
        b1.setBackground(Color.green);

        JButton b2 = new JButton(" Button2 ");
        b2.setBackground(Color.yellow);

        panel.add(b1);
        panel.add(b2);
        add(panel);
        setSize(300, 200);
//        pack();
        setLocation(1300, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        PanelTest f = new PanelTest();
    }
}
