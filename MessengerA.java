package compro2.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class MessengerA {
    protected JTextField textField;
    protected JTextArea textArea;
    DatagramSocket datagramSocket;
    DatagramPacket datagramPacket;
    InetAddress inetAddress = null;
    final int myPort = 5000;
    final int otherPort = 6000;

    public MessengerA() throws IOException {
        MyFrame myFrame = new MyFrame();
        inetAddress = InetAddress.getByName("127.0.0.1");
        datagramSocket = new DatagramSocket(myPort);
    }

    public void process() {
        while (true) {
            try {
                byte[] buf = new byte[256];
                datagramPacket = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(datagramPacket);
                textArea.append("RECEIVED" + new String(buf) + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class MyFrame extends JFrame implements ActionListener {
        public MyFrame() {
            super("MessengerA");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            textField = new JTextField(30);
            textField.addActionListener(this);
            textArea = new JTextArea(10, 30);
            textArea.setEditable(false);
            add(textField, BorderLayout.PAGE_END);
            add(textArea, BorderLayout.CENTER);
            pack();
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
