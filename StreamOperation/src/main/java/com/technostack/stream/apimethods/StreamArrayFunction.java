package com.technostack.stream.apimethods;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamArrayFunction {
    public static void main(String[] args) {

        // Creating Stream of Integers
        Stream<String> integerStream = Stream.of("1", "2", "3", "4", "5", "6");
        //converting the integer stream to String[] array
        String[] array = integerStream.toArray(String[]::new);
        //printing the array
        System.out.println("Array of Strings: " + Arrays.toString(array));
    }
}
