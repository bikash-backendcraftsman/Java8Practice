package com.technostack.stream.apimethods;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamReduceFunction {

    /**
     * Key Points
     * 1. The Java Stream reduce() method is a terminal operation that can reduce all elements in the stream to a single element.
     * 2. It takes a binary operator to combine all elements of the stream into a single result.
     * 3. The method can be overloaded; one takes an identity value and a binary operator, another
     * only takes a binary operator and returns an Optional.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Creating a stream of numbers
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        // Reduce with binary operator
        integerStream.reduce(Integer::sum).ifPresent(p -> System.out.println(p));

        // Re-create the stream as it has already been consumed
        integerStream = Stream.of(1, 2, 3, 4, 5);
        // Reduce with identity and BinaryOperator (sum)
        int sumWithIdentity = integerStream.reduce(0, Integer::sum);
        System.out.println("Sum with identity: " + sumWithIdentity);

        // Empty Stream example
        Stream<Integer> emptyStream = Stream.empty();

        // Reduce empty stream with identity

        /**
         * numbers.reduce(0, Integer::sum) starts with an identity (0) and applies the sum operation,
         * ensuring a default result even for an empty stream.
         *
         * Stream.empty() creates an empty stream of integers.
         *
         * emptyStream.reduce(0, Integer::sum) demonstrates how the identity value ensures that we
         * get a meaningful result (0 in this case) even when the stream is empty.
         */
        int sumOfEmptyStream = emptyStream.reduce(0, Integer::sum);
        System.out.println("Sum of empty stream: " + sumOfEmptyStream);

    }
}
