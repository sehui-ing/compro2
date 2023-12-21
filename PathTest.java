package compro2.ex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PathTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\sources\\test.txt");
        System.out.println("전체 경로 : " + path);
        System.out.println("파일 이름 : " + path.getFileName());
        System.out.println("부모 이름 : " + path.getParent().getFileName());

        Files.list(Paths.get(".")).forEach(System.out::println);
        Files.lines(new File("test.txt").toPath())
                .map(s -> s.trim())
                .filter(s -> !s.isEmpty())
                .forEach(System.out::println);
    }
}
