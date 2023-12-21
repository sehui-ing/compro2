package compro2.ex;

import javax.swing.*;
import java.awt.*;

public class ComponentEx extends JFrame {
    private JPanel panel, panel2, panel3;
    private JLabel label1;
    private JButton button1, button2;

    public ComponentEx() {
        setTitle( " 회원 정보 \n ");
        setSize( 275, 300 );
        setLocation(1500, 240);

        panel = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel("  회 원   등 록 하 기  ");
        label1.setForeground(Color.BLACK);

        panel2.add(label1);
        add(panel2, BorderLayout.NORTH);
        add(panel);

        panel.add(new JLabel(" 이름 "));
        panel.add(new JTextField(15));
        panel.add(new JLabel(" 패스워드 "));
        panel.add(new JPasswordField(15));

        panel.add(new JLabel(" 이메일 주소 "));
        panel.add(new JTextField(15));
        panel.add(new JLabel(" 전화번호 "));
        panel.add(new JTextField(15));

        button1 = new JButton(" 등록하기 ");
        panel.add(button1);
        button2 = new JButton(" 취소 ");
        panel.add(button2);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ComponentEx();
    }
}
