package compro2.ex;

import java.io.*;
import java.util.*;

public class StremTest {
    public static void main(String[] args) throws Exception {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        int sum = integerList.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }
}
