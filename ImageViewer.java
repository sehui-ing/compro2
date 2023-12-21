package compro2.ex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageViewer extends JFrame implements ActionListener {
    private JLabel label;
    private JMenuItem open, exit;
    public ImageViewer() {
        setTitle("ImageViewer");
        setSize(350, 200);
        JMenuBar mbar = new JMenuBar();
        JMenu m = new JMenu("File");
        open = new JMenuItem("Open");
        open.addActionListener(this);
        m.add(open);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        m.add(exit);
        mbar.add(m);
        setJMenuBar(mbar);
        label = new JLabel();
        JPanel panel = new JPanel();
        panel.add(label, "Center");
        add(panel);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == open) {
            JFileChooser chooser = new JFileChooser();
            int r = chooser.showOpenDialog(this); //파일 오픈 다이얼로그 창 열기
            if (r == JFileChooser.APPROVE_OPTION) { //정상적인 열기가 수행된 경우
                String name = chooser.getSelectedFile().getAbsolutePath(); //선택한 파일의 절대경로 반환
                label.setIcon(new ImageIcon(name));
            }
        } else if (source == exit)
            System.exit(0);
    }
    public static void main(String[] args) {
        ImageViewer f = new ImageViewer();
    }
}
