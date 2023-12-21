package compro2.ex;

import java.io.*;
import java.util.*;

public class ObjectStreamTest {
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        out = new ObjectOutputStream(new FileOutputStream("object.dat"));
        out.writeObject(new Date());
        out.close();

        in = new ObjectInputStream(new FileInputStream("object.dat"));
        Date date = (Date) in.readObject();
        System.out.println(date);
        in.close();
    }
}
