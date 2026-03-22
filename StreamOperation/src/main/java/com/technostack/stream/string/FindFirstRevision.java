package com.technostack.stream.string;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFirstRevision {
    public static void main(String[] args) {
        String input = "a";
        char c = firstNonRepetingChar(input);
        System.out.println(c);
    }

    private static char firstNonRepetingChar(String input) {
      return input.chars()
                //  convert ASCI to char  for further stream operation
                .mapToObj(ch -> (char) ch) //  convert ASCI to char  for further stream operation
               //used to count the frequency and maintain the insertion order using LinkedHashMap
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
              // as I got map, map contain  multiple Entry<<Key,Value>>, so to convert we have used entrSet()
                .entrySet()
              // we cant use stream opertion on direct collection, so we convert Se<Entry<Key,Value>> to Stream<Entry<Key,Value>> to use the stream methods
              // and from the entries filter we get the  frequency /count of character whose value is 1, [LinkedHashMap : Maintain insertion Order]
                .stream().filter(e -> e.getValue() == 1)
              // now we get the value, we have to get the ony matching character whose value is 1, (which is first value), we use get the charcter by using getKey() Method
                .map(Map.Entry::getKey)
              //use findFirst() method to get the value
                .findFirst().orElse('\0');


    }

    /**
     * The pattern to burn in for interviews
     * Every time you write a stream pipeline, ask yourself one question at each step: "what type do I have, and what type do I need?"
     *
     * I have a Map — I need to iterate its pairs → call .entrySet()
     * I have a Set — I need to filter/transform → call .stream()
     * I have a Stream<Entry> — I need to narrow it → call .filter()
     * I have a Stream<Entry> — I need just the key → call .map(getKey)
     * I have a Stream<Character> — I need one result → call .findFirst()
     */
}
