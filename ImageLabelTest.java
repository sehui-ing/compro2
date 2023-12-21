package compro2.ex;

import javax.swing.*;

public class ImageLabelTest extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JButton button;

    public ImageLabelTest() {
        setTitle("레이블 테스트");
        setSize(400, 700);
        setLocation(1500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        label = new JLabel("game");
        ImageIcon icon = new ImageIcon("C://Users//jsh14//maple.png");
        label.setIcon(icon);

        button = new JButton("자세한 정보를 보려면 클릭하세요");

        panel.add(label);
        panel.add(button);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        ImageLabelTest test = new ImageLabelTest();
    }
}
