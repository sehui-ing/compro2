package compro2.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

class MidtermExam extends JFrame {
    private JButton button;
    private JLabel label;
    int counter = 0;

    class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            counter++;
            label.setText(counter + "");
        }
    }
    public MidtermExam() {
        label = new JLabel(counter + "", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        button = new JButton("증가");
        button.addActionListener(new MyListener());
        add(button, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        List al = new ArrayList();
        List ll = new LinkedList();
        al.add(111);
        al.add(222);
        al.add(1, 333);
        System.out.println(al);
        System.out.println(al.size());
        System.out.println(al.indexOf(333));
        System.out.println(al.subList(1, 3));

        ll.add("MILK");
        ll.add("BREAD");
        ll.add("BUTTER");
        System.out.println(ll);

        ll.set(2, "APPLE");
        System.out.println(ll);

        Collections.sort(al);
        al.sort(null); // null은 비교대상을 따로 지정하지 않고 default 값으로 진행한다.
        System.out.println(al); // al을 정렬하려는 경우, al에 저장된 객체의 클래스에서 반드시 compareTo()를 구현해야 한다.

        int sum = (Integer) al.get(0) + (Integer) al.get(2);
        System.out.println(sum);

        List<Integer> arrayList = new ArrayList<Integer>();
        List<String> linkedList = new LinkedList<String>();

        Iterator<Integer> ial = al.iterator();
        while (ial.hasNext()) {
            ial.next();
            ial.remove();
        }

        enum Direction {EAST, SOUTH, WEST, NORTH}
        Direction d = Direction.EAST;
    }
}
