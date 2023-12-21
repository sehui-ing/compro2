package compro2.ex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileTest {
    public static void main(String[] args) throws IOException {
        String name = "c:/Temp";
        File directory = new File(name);
        String[] fileNames = directory.list();
        for (String s : fileNames) {
            File file = new File(name + "/" +s);
            System.out.println("============================");
            System.out.println("이름 : " + file.getName());
            System.out.println("경로 : " + file.getPath());
            System.out.println("절대 경로 : " + file.getAbsolutePath());
            System.out.println("정규 : " + file.getCanonicalPath());
            System.out.println("디렉토리 여부 : " + file.isDirectory());
            System.out.println("파일 여부 : " + file.isFile());
            System.out.println("============================");
        }

        Files.list(Paths.get("./src")).forEach(System.out::println);
    }
}
