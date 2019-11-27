package tester;

import java.util.Arrays;
import java.util.List;

public class StreamTester {
    public static void main(String[] args) {
        List<Integer> aliceArray = Arrays.asList(12, 78, 23);
        List<Integer> bobArray = Arrays.asList(12, 34, 55);

        List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
        List<String> people2 = Arrays.asList("Al", "Ankit", "nora", "TRob", "Wein", "Hans", "Shivika", "hary");
//        Stream<Integer> resultArray = aliceArray
//                .stream()
//                .map(item -> bobArray
//                        .stream()
//                        .filter(item::equals));
       people
                .stream()
                .map(item -> people2
                        .stream()
                        .anyMatch(item2 -> item2.equals(item)))
                .forEach(System.out::println);

       System.out.println("number comparison");
        aliceArray
                .stream()
                .map(item -> bobArray.stream()
                                        .map(item2->item > item2 ? 1: 0)
                )
                .forEach(System.out::println);
    }
}
