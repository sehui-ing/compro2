package compro2.ex;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.SocketHandler;

public class FunctionTest {
    public static void main(String[] args) {
        Function<Integer, Integer> f1 = i -> i*4;
        System.out.println(f1.apply(3));
        Function<String, Integer> f2 = s -> s.length();
        System.out.println(f2.apply("Hello"));

        Supplier<String> supplier = () -> "Hello Java";
        Consumer<Integer> consumer = i -> System.out.println();
        Predicate<String> predicate = v -> v != null;
        System.out.println();
    }
}
