package compro2.ex;
import javax.swing.*;

public class AnonymousClassTest extends JFrame {
    private JLabel label;
    private JButton button;

    int counter = 0;

    public AnonymousClassTest() {
        this.setSize(400, 150);
        this.setTitle(" 이벤트 예제 ");

        JPanel panel = new JPanel();
        label = new JLabel(" 현재 카운트 : " + counter);
        button = new JButton(" 증가 ");

//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                counter ++;
//                label.setText(" 현재 카운트 : " + counter);
//            }
//        });

        button.addActionListener(e -> {
            counter ++;
            label.setText( " 현재 카운트 : " + counter);
        });

        panel.add(label);
        panel.add(button);
        add(panel);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        AnonymousClassTest t = new AnonymousClassTest();
    }
}
