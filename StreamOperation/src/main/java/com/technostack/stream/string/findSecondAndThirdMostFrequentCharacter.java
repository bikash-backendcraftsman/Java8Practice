package com.technostack.stream.string;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;

public class findSecondAndThirdMostFrequentCharacter {
    public static void main(String[] args) {
        String input = "aabbbbccdddd";
        List<Character> val =  findFrequentCharacter(input);
        System.out.println(val);
    }

    private static List<Character> findFrequentCharacter(String input) {
        Map<Character, Long> collect = input
                .chars()
                //Convert String to Stream of Characters
                .mapToObj(c -> (char) c)
               // Build frequency map and Character count
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //Map has no stream ops. Get entries (both key+value together) as a streamable Set
        Stream<Map.Entry<Character, Long>> stream = collect.entrySet().stream();
        //Map.Entry has no natural order — must tell it to sort by count descending
        return stream.sorted(comparingByValue(Comparator.reverseOrder()))
        //Discard count, extract only the character — order already set by sorted()
                .map(Map.Entry::getKey)
                //Skip position 0 (the most frequent)
                .skip(1)
                //Take exactly 2 elements — the 2nd and 3rd most frequent
                .limit(2)
                //Terminal operation — triggers pipeline, collects result
                .collect(Collectors.toList());

    }
}
