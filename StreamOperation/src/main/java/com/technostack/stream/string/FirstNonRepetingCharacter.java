package com.technostack.stream.string;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstNonRepetingCharacter {
    public static void main(String[] args) {
        String input = "aabbccdee";
        char ch = firstNonRepetingCharacter(input);
        System.out.println(ch);
    }

    private static char firstNonRepetingCharacter(String input) {
        LinkedHashMap<Character, Long> collect = input.chars().mapToObj(v -> (char) v)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        /**
         * Step 1 — .entrySet()
         * Map → Set>
         * What does .entrySet() actually return?
         * It returns a Set of Map.Entry objects. Each Map.Entry is a single key-value pair bundled together — so you get both the character AND its count in one object.
         * freq.entrySet() // Returns a Set containing these Entry objects: [ Entry{ key='a', value=2 }, Entry{ key='b', value=2 }, Entry{ key='c', value=1 }, // what we want Entry{ key='d', value=2 }, Entry{ key='e', value=1 } ]
         * Why not just use .keySet() or .values()?
         * Because we need BOTH the key AND the value at the same time. .keySet() gives only characters — we can't check counts. .values() gives only counts — we can't get the character back. .entrySet() keeps them paired together.
         * What's the type now?
         * type transformation:
         * Map
         * →
         * Set>
         */
        Set<Map.Entry<Character, Long>> entries = collect.entrySet();

        /**
         * Step 2 — .stream()
         * Set> → Stream>
         * Why do we need .stream() at all?
         * Set does not have .filter(), .map(), or .findFirst() methods. These operations belong to the Stream API, not to collections. .stream() is the bridge — it converts a collection into a pipeline you can chain operations on.
         * What is a Stream exactly?
         * A Stream is NOT a data structure. It holds no data. It's a pipeline of operations to be applied to a source. Think of it like a conveyor belt — items pass through one by one, each operation processes them.
         * // Set = a bag of items (holds data) Set<Map.Entry<...>> set = freq.entrySet(); // Stream = a pipeline (no data, just operations) Stream<Map.Entry<...>> s = set.stream(); // Nothing has happened yet! Streams are lazy.
         * Important: streams are lazy!
         * Calling .stream() does NOT iterate anything yet. Nothing runs until you call a terminal operation like .findFirst() or .collect(). The entire pipeline is built first, then executed in one pass.
         * What's the type now?
         * type transformation:
         * Set>
         * →
         * Stream>
         */
        Stream<Map.Entry<Character, Long>> stream = entries.stream();

        /**
         * Step 3 — .filter(e -> e.getValue() == 1)
         * Stream → Stream (smaller — only unique chars)
         * What does .filter() do?
         * .filter() keeps only the entries that pass a test (a Predicate — a function that returns true/false). Every entry where the test returns false is dropped from the stream. The type stays the same.
         * // The lambda: e -> e.getValue() == 1 // e = one Map.Entry<Character, Long> // getValue() = the Long count for that char // == 1 = is it unique? Entry{ 'a', 2 } → getValue()=2 → 2==1 is false → DROPPED Entry{ 'b', 2 } → getValue()=2 → 2==1 is false → DROPPED Entry{ 'c', 1 } → getValue()=1 → 1==1 is true → KEPT Entry{ 'd', 2 } → getValue()=2 → 2==1 is false → DROPPED Entry{ 'e', 1 } → getValue()=1 → 1==1 is true → KEPT
         * What's left in the stream after filter?
         * what's in the stream after filter:
         * 'a' → 2
         * 'b' → 2
         * 'c' → 1
         * 'd' → 2
         * 'e' → 1
         * Only 'c'→1 and 'e'→1 remain.
         * Why .getValue() and not just .value?
         * Map.Entry is an interface — you access its contents via .getKey() and .getValue(). You can't write e.value or e.count because Entry fields are private.
         * What's the type now?
         * type transformation:
         * Stream>
         * → filter →
         * Stream>
         * ← Previous
         * Next →
         */
        Stream<Map.Entry<Character, Long>> entryStream = stream.filter(e -> e.getValue() == 1);

        /**
         * Step 4 — .map(Map.Entry::getKey)
         * Stream> → Stream
         * What does .map() do?
         * .map() transforms each element in the stream into something else. It takes a Function<T, R> — give it one type, get back another. Here we transform each Entry into just its key (the Character). The count is discarded because we no longer need it.
         * Entry{ 'c', 1 } → Map.Entry::getKey → 'c' Entry{ 'e', 1 } → Map.Entry::getKey → 'e' // Stream> // becomes // Stream
         * What is Map.Entry::getKey?
         * It's a method reference — shorthand for the lambda e -> e.getKey(). Both do exactly the same thing. Method references are preferred in Java 8 when the lambda just calls one method with no extra logic.
         * // These two are identical: .map(e -> e.getKey()) // lambda version .map(Map.Entry::getKey) // method reference (cleaner)
         * Why do we need .map() at all? Why not skip it?
         * Without .map(), the stream contains Map.Entry objects. .findFirst() would return an Optional<Map.Entry<Character,Long>>. You'd then need an extra .get().getKey(). The .map() extracts the character BEFORE findFirst, so you get back a clean Optional<Character> directly.
         * What's the type now?
         * type transformation:
         * Stream>
         * → map →
         * Stream
         */

        Stream<Character> characterStream = entryStream.map(Map.Entry::getKey);

        /**
         * Step 5 — .findFirst()
         * Stream → Optional
         * What does .findFirst() do?
         * .findFirst() is a terminal operation — it triggers the entire pipeline to actually execute, and returns the first element in the stream wrapped in an Optional. If the stream is empty, you get Optional.empty() instead of throwing an exception.
         * // Stream after filter + map contains: ['c', 'e'] .findFirst() // Returns Optional.of('c') // 'c' is first because LinkedHashMap preserves insertion order .orElse('') // Unwraps the Optional. If empty, returns null char '' // Final result: 'c'
         * What is Optional?
         * Optional<T> is a wrapper that might or might not contain a value. It forces you to handle the 'not found' case explicitly instead of getting a NullPointerException. .orElse('\0') means: 'give me the value, or if there is none, give me null char instead'.
         * Why is .findFirst() called a 'terminal operation'?
         * Because it ends the stream pipeline and produces a result. Streams are lazy — .filter() and .map() are 'intermediate operations' that just describe what to do. Nothing actually runs until .findFirst() fires. It's the trigger that starts the whole thing.
         * What's the type now?
         * type transformation:
         * Stream
         * → findFirst →
         * Optional
         * → orElse →
         * char
         */

        Character c = characterStream.findFirst().orElse('\0');

        return c;
    }
}
