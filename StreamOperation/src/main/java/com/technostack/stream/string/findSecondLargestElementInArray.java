package com.technostack.stream.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class findSecondLargestElementInArray {
    public static void main(String[] args) {
        int[] array = {12,55,95,3,200,99};
       int n =  filterSecondLargest(array);
        System.out.println(n);
    }

    private static int filterSecondLargest(int[] array) {
        if(array.length == 0){
            return -1;
        }

       return Arrays.stream(array)
                .distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().orElse(0);
    }
}
