package com.technostack.stream.operation;

import com.technostack.stream.model.Order;
import com.technostack.stream.model.OrderItem;
import com.technostack.stream.model.SampleData;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperationWeek2 {
    public static void main(String[] args) {

        //flatMap + collect(toList) → flatten all order items

        List<Order> orders = SampleData.orders();

        /**
         * A -> Shape:
         * Each Order contains a List<OrderItem>.
         * flatMap is used to flatten Stream<Order> into Stream<OrderItem>.
         *
         * B -> Collect:
         * Collect all OrderItem elements into a List.
         *
         * C -> Consume:
         * Print the flattened list.
         */

        Stream<OrderItem> orderItemStream = orders.stream().flatMap(order -> order.getItems().stream());
        List<OrderItem> collect = orderItemStream.collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("==================================================================================");

        //flatMap + filter + distinct → unique brands bought in delivered orders

        /**
         * ✅ Correct (A → B → C) solution for “unique brands”
         * A) Shape: delivered orders → all items
         * B) Transform: items → brand
         * C) Finalize: distinct brands → list
         */
        List<String> collect1 = orders.stream()
                .filter(order -> "DELIVERED".equals(order.getStatus()))
                .flatMap(order -> order.getItems().stream())
                .map(OrderItem::getBrand)
                .filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());

        System.out.println(collect1);

        System.out.println("==================================================================================");

        //sorted(Comparator) → items sorted by revenue (qty*price)

        orders.stream().flatMap(order -> order.getItems().stream())
                .sorted(Comparator.comparingDouble(item -> printRevinue(item.getQty(), item.getUnitPrice())))
                .map(OrderItem::getName)
                .filter(Objects::nonNull)
                .forEach(System.out::println);

        System.out.println("==================================================================================");

        // mapToInt + sum (IntStream) → total quantity across items

        /**
         * ✔️ Verdict
         *
         * ✅ Correct logic
         *
         * ✅ Correct stream choice (IntStream)
         *
         * ✅ No boxing overhead
         *
         * ✅ Clean terminal operation (sum())
         */
        int sum = orders.stream().flatMap(order -> order.getItems().stream())
                .mapToInt(OrderItem::getQty).sum();
        System.out.println("All quantity Sum :"+ sum);

        System.out.println("==================================================================================");

        //mapToDouble + average (DoubleStream) → average unit price

        /**
         * Stream<Order>
         *    ↓ flatMap
         * Stream<OrderItem>
         *    ↓ mapToDouble
         * DoubleStream
         *    ↓ average
         * OptionalDouble
         */

        /**
         * A — Shape (Data extraction)
         *
         * Start from Stream<Order>
         *
         * Flatten nested List<OrderItem> into one Stream<OrderItem> using flatMap
         *
         * Now you’re working with a single flow of items (not list-of-lists)
         *
         * B — Convert (Numeric stream for math)
         *
         * Turn OrderItem into a primitive numeric stream
         *
         * Use mapToDouble (not map) so the stream becomes DoubleStream
         *
         * Reason: average() exists on DoubleStream, not on Stream<Double>
         *
         * C — Finalize (Terminal + handling)
         *
         * Apply terminal operation average()
         *
         * Handle result type: it’s an OptionalDouble (could be empty if no items)
         *
         * Decide output strategy: print if present OR default value (orElse)
         */
        double asDouble = orders.stream().flatMap(order -> order.getItems().stream())
                .mapToDouble(OrderItem::getUnitPrice).average().getAsDouble();
        System.out.println(asDouble);

        System.out.println("==================================================================================");


        // boxed() → convert IntStream to Stream<Integer> need guidance

        /**
         * 🔹 What boxed() is (one-line)
         *
         * boxed() converts a primitive stream (IntStream, LongStream, DoubleStream) into an object stream (Stream<Integer>, etc.)
         *
         * 🧠 Why boxed() exists (the real reason)
         *
         * Java Streams have two worlds:
         *
         * 1️⃣ Primitive Stream world
         *
         * IntStream
         *
         * LongStream
         *
         * DoubleStream
         *
         * 👉 Fast, memory-efficient
         * 👉 But limited operations
         *
         * 2️⃣ Object Stream world
         *
         * Stream<Integer>
         *
         * Stream<Order>
         *
         * Stream<String>
         *
         * 👉 Rich operations (collect, groupingBy, Comparator, Map, etc.)
         * 👉 Uses wrapper objects
         *
         * 🚧 Problem
         * Primitive streams cannot work with APIs that expect objects
         *
         * ➡️ boxed() is the bridge
         */

        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        List<Integer> collect2 = intStream.boxed().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(collect2);

    }








    /**
     * 2) “comparingDouble needs 1 parameter” — so how does 2 args work?
     * What comparingDouble actually wants
     *
     * Comparator.comparingDouble(...) expects a function:
     *
     * ToDoubleFunction<OrderItem> keyExtractor
     *
     *
     * Meaning:
     * Given an OrderItem, return a double (the sorting key).
     *
     * Your lambda:
     *
     * item -> printRevinue(item.getQty(), item.getUnitPrice())
     *
     *
     * takes one parameter: item.
     *
     * Inside the body, you call your method with two values extracted from item:
     *
     * item.getQty()
     *
     * item.getUnitPrice()
     *
     * So you are NOT passing 2 parameters to comparingDouble.
     * You are passing one function that uses the item to compute the key.
     *
     * Equivalent expanded version (to see it clearly)
     * ToDoubleFunction<OrderItem> keyExtractor = item -> {
     *     int q = item.getQty();
     *     double p = item.getUnitPrice();
     *     return printRevinue(q, p);
     * };
     *
     * Comparator<OrderItem> cmp = Comparator.comparingDouble(keyExtractor);
     *
     *
     * That’s what’s happening internally.
     * @param quant
     * @param unitPrice
     * @return
     */
    static double printRevinue(int quant, double unitPrice){
        return  quant * unitPrice;

        /**
         * 4) Small note: comparingInt vs comparingDouble
         *
         * If revenue can be fractional (double price), your comparingDouble is perfect ✅
         *
         * If revenue is always integer (price int), use comparingInt for simplicity.
         */
    }

}
