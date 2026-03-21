package com.technostack.stream.operation;

import com.technostack.stream.model.Order;
import com.technostack.stream.model.OrderItem;
import com.technostack.stream.model.Payment;
import com.technostack.stream.model.SampleData;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationWeek1 {

    /**
     * Final Mental Model (memorize this)
     *
     * Checkpoint A: What shape do I have?
     * Checkpoint B: What shape do I want?
     * Checkpoint C: How do I collect it?
     *
     */

    /**
     * 0) The ONE sentence that removes confusion
     *
     * A = create the stream you need
     * B = collect into a Map (bucket/summarize)
     * C = stream the Map again to produce final output
     *
     * That’s it.
     *
     */
    public static void main(String[] args) {

        //Goal: map + collect(toList) → item names list

        List<Order> orders = SampleData.orders();

        Stream<Order> orderStream = orders.stream();

        Stream<OrderItem> orderItemStream = orderStream.flatMap(o -> o.getItems().stream());

        Stream<String> stringStream = orderItemStream.map(OrderItem::getName);

        List<String> collect = stringStream.collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("=============================================");

        // filter + collect(toList) → items with qty > 1
        Stream<Order> orderStreamData = orders.stream();
       // Stream<List<OrderItem>> listStream = orderStreamData.map(o -> o.getItems());

        Stream<OrderItem> orderItemStream1 = orderStreamData.flatMap(order -> order.getItems().stream());

        Stream<OrderItem> orderItemStream2 = orderItemStream1.filter(p -> p.getQty() > 1);
        List<OrderItem> collect1 = orderItemStream2.collect(Collectors.toList());
        System.out.println(collect1);

        /**
         * 🧠 Simple Rule (Never Forget This)
         * Situation	Method
         * 1 → 1	 :  map
         * 1 → many	 :  flatMap
         * many → 1	 :  reduce
         * selection :	filter
         */


        //distinct + sorted + collect(toList) → distinct categories sorted.

        /**
         * 🧠 Mental Model First (VERY IMPORTANT)
         *
         * Before code, answer these 3 questions:
         *
         * What is my starting shape? [✅ Checkpoint A — Shape the Data]
         * Goal: Reach the exact element you want (String category)
         * 👉 Stream<Order>
         *
         * What is my target shape? [✅ Checkpoint B — Bucket / Clean / Order]
         * Goal: Remove duplicates + sort
         * 👉 List<String> (categories)
         *
         * Is there nesting? [✅ Checkpoint C — Finalize]
         * Goal: Convert stream → concrete structure
         * 👉 Yes → Order → List<OrderItem> → category
         *
         * Because of nesting → flatMap is mandatory
         */

        Stream<Order> stream = orders.stream();
        //from Stream<order> want to get Stream<OrderItem>
        Stream<OrderItem> orderItemStream3 = stream.flatMap(order -> order.getItems().stream());
        // From Stream<orderItem> want to get the item category
        Stream<String> categoryStream = orderItemStream3.map(OrderItem::getCategory);
        // from category , get only distinct elements and sort it and collect all category to a list
        List<String> collect2 = categoryStream.distinct().sorted().collect(Collectors.toList());
        System.out.println(collect2);

        System.out.println("==============================================================");
        // count → count delivered orders
        Stream<Order> orderData = orders.stream();
        Stream<Order> stringStream1 = orderData.filter(order -> order.getStatus().equals("DELIVERED"));
        long count = stringStream1.count();
        System.out.println("DELIVERED Status count :"+count);

        System.out.println("=======================");

        //min/max(Comparator) → cheapest & costliest item

        /**
         * Yes — your approach is correct ✅
         *
         * You did:
         *
         * A (Shape data): orders.stream().flatMap(order -> order.getItems().stream()) → converts Stream<Order> into Stream<OrderItem>
         *
         * B (Finalize / find result): max(...) and min(...) → gives you the costliest and cheapest OrderItem
         *
         * C (Consume): ifPresent(System.out::println) → prints
         *
         * So the thinking is right.
         */
        List<OrderItem> items = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.toList());

        items.stream()
                .max(Comparator.comparingDouble(OrderItem::getUnitPrice))
                .ifPresent(i -> System.out.println("Max val: " + i));

        items.stream()
                .min(Comparator.comparingDouble(OrderItem::getUnitPrice))
                .ifPresent(i -> System.out.println("Min val: " + i));


        System.out.println("========================================");
        //toArray() → convert item stream to array

        /**
         * Why it feels correct (and actually is – conceptually)
         * ✅ Checkpoint A — Shape the data (CORRECT)
         * orders.stream()
         *       .flatMap(order -> order.getItems().stream())
         *
         * Stream<Order> → Stream<OrderItem>
         *
         * ✔ Correct use of flatMap
         *
         * ✅ Checkpoint B — Resize / transform (CORRECT)
         * .map(OrderItem::getName)
         *
         * Stream<OrderItem> → Stream<String>
         *
         * ✔ Perfect
         *
         * ❌ Checkpoint C — Finalize (THIS is the gap)
         * .toArray();
         *
         *
         * This is where Java needs clarity of the target type.
         *
         * What exactly is the problem with toArray()?
         * What Java does internally
         * Object[] arr = stream.toArray();
         *
         *
         * So your result becomes:
         *
         * Object[]
         *
         *
         * ❌ NOT String[]
         * ❌ Requires casting
         * ❌ Breaks type safety
         * ❌ Sonar & interviewers don’t like this
         *
         * Correct thinking direction (HINT, not full spoon-feed)
         *
         * Ask yourself 👇
         *
         * “What exact array type do I want at the end?”
         *
         * Your stream is:
         *
         * Stream<String>
         *
         *
         * So final result should be:
         *
         * String[]
         *
         * Direction to correct (key idea 💡)
         *
         * Java Stream API provides two versions of toArray():
         *
         * 1️⃣ No-arg → gives Object[]
         * 2️⃣ Generator-based → gives typed array
         *
         * You need version 2
         *
         * Hint syntax (focus on concept, not copy-paste)
         *
         * Think like this:
         *
         * toArray(size -> new String[size])
         *
         *
         * or even simpler (method reference idea 💡):
         *
         * toArray(String[]::new)
         *
         *
         * ➡️ This tells Java:
         *
         * “Hey, create a String[] of required size and fill it”
         */

        String[] array = orders.stream().flatMap(order -> order.getItems().stream())
                .map(OrderItem::getName).toArray(String[]::new);
        Stream.of(array).forEach(System.out::println);

        System.out.println("========================================================");


        //anyMatch/allMatch/noneMatch → validations (any expensive? all valid qty? none negative?)

        /**
         * 💡 Mental rule
         *
         * ❝ A stream is like a pipe — once water flows, you can’t reuse it ❞
         */
        Stream<OrderItem> orderItemStream4 = orders.stream().flatMap(order -> order.getItems().stream());
        boolean validQuantity = orderItemStream4.allMatch(item -> item.getQty() > 0);
        System.out.println(validQuantity);

        boolean nonNegative = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .noneMatch(item -> item.getQty() < 0);
        System.out.println("Non Negative :"+nonNegative);

        System.out.println("================================================");

        //findFirst/findAny → first delivered order, any failed payment

        Optional<Order> delivered = orders.stream().filter(order -> order.getStatus().equals("DELIVERED")).findFirst();
        if(delivered.isPresent()){
            Order order = delivered.get();
            System.out.println(order);
        }

        Stream<Payment> paymentStream = orders.stream().flatMap(order -> order.getPayments().stream());
        Optional<Payment> failed = paymentStream.filter(payment -> "FAILED".equals(payment.getStatus())).findAny();
        if(failed.isPresent()){
            Payment payment = failed.get();
            System.out.println(payment);
        }

        //forEach → print category wise items (simple)
    }
}
