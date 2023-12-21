package compro2.ex;
import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    private JPanel panel1, panel2, panel3;
    private JTextField textField;
    private JButton[] buttons;
    private String[] labels = {
            "7", "8", "9", "/", "4", "5", "6", "*",
            "1", "2", "3", "-", "0", "back", "=", "+"
    };

    public Calculator() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        JTextField outputField = new JTextField(35);

        setTitle(" 계산기 ");
        setLocation(700, 300);

        outputField.setText("0.");
        outputField.setEnabled(false);
        panel1.add(outputField);
        panel2.add(new JButton("C"));
        panel3.setLayout(new GridLayout(4, 4, 4, 4));

        buttons = new JButton[16];
        int index = 0;
        for (int rows = 0; rows < 4; rows++) {
            for (int cols = 0; cols < 4; cols++) {
                buttons[index] = new JButton(labels[index]);
                panel3.add(buttons[index]);
                index++;
            }
        }

        panel3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        add(outputField, BorderLayout.NORTH);
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.WEST);
        add(panel3, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void print(String s) {
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}
