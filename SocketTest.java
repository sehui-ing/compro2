package compro2.ex;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.*;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("time-c.nist.gov", 13)) {
            InputStream inputStream = s.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
//    public static void main(String[] args) throws IOException {
//        try (Socket s = new Socket("time-c.nist.gov", 13)) {
//            InputStream inStream = s.getInputStream();
//            Scanner in = new Scanner(inStream);
//            while (in.hasNextLine()) {
//                String line = in.nextLine();
//                System.out.println(line);
//            }
//        }
//    }
    }
}
