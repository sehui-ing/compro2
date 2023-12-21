package compro2.ex;

import java.io.*;
import java.util.*;

public class DataStreamExample {
    public static void main(String[] args) {
        DataInputStream in  = null;
        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new FileOutputStream("data.txt"));
            out.writeInt(123);
            out.writeDouble(123.456);
            out.writeChar('A');
            out.writeBoolean(true);

            in = new DataInputStream(new FileInputStream("data.txt"));
            int aInt  = in.readInt();
            double aDouble = in.readDouble();
            char aChar = in.readChar();
            boolean aBool = in.readBoolean();
            System.out.println(aInt + ", " + aDouble + ", " + aChar + ", " + aBool);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
