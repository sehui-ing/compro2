package compro2.ex;

import javax.swing.*;

public class Practice extends JFrame {
    private JPanel panel;
    private JLabel label1, label2;



    public void ComponentEx() {
        panel.add(new JLabel("id "));
        panel.add(new JTextField(15));
        panel.add(new JLabel("pass"));
        panel.add(new JPasswordField(15));

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ComponentEx();
    }
}
