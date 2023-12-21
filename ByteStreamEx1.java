package compro2.ex;

import java.io.*;
import java.util.*;

public class ByteStreamEx1 {
    public static void main(String[] args) throws FileNotFoundException {
        byte[] list = {100, 110, 120};
        try (FileOutputStream fileOutputStream = new FileOutputStream("test3.txt")) {
            for (byte b : list) {
                fileOutputStream.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("쓰기 완성");
        }

        byte[] blist = new byte[3];
        try (FileInputStream fileInputStream = new FileInputStream("test3.txt")) {
            fileInputStream.read(blist);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("읽기 완성");
        }

        for (byte b : blist) {
            System.out.println(b + " ");
        }
    }
}
