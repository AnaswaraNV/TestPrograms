package tester;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTester {
    public static void main(String[] args) {
        List<Integer> aliceArray = Arrays.asList(12, 78, 23);
        List<Integer> bobArray = Arrays.asList(12, 34, 55);
        List<Integer> nonUniqueArray = Arrays.asList(1, 1, 2, 4, 5, 5, 6);

        List<Integer> array1 = Arrays.asList(1, 5, 8, 9, 15);
        List<Integer> array2 = Arrays.asList(2, 7, 13, 14, 15);

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

        System.out.println("find non unique elements");
        Set<Integer> values  = nonUniqueArray.stream()
                      .filter(i -> Collections.frequency(nonUniqueArray , i) > 1)
                        .collect(Collectors.toSet());

        long nonUniqueCount = nonUniqueArray.stream()
                .filter(i -> Collections.frequency(nonUniqueArray , i) > 1)
                .distinct()
                .count();
        System.out.println("find non unique elements - values" + values);
        System.out.println("find non unique elements - count" + nonUniqueCount );

        List<Integer> newList = Stream.concat(array1.stream(), array2.stream())
                                        .sorted()
                                        .collect(Collectors.toList());
        System.out.println("sorted" + newList );
    }
}
