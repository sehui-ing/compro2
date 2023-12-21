package compro2.ex;
import java.awt.*;
import javax.swing.*;
public class FlowLayoutTest extends JFrame {
    public FlowLayoutTest() {
        setTitle(" FlowLayoutTest ");
        setSize(500, 300);

        setLayout(new FlowLayout());

        add(new JButton("b1"));
        add(new JButton("b2"));
        add(new JButton("b3"));
        add(new JButton("b4"));
        add(new JButton("b5"));

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        FlowLayoutTest f = new FlowLayoutTest();
    }
}
