package com.technostack.stream.operation;

import com.technostack.stream.model.Order;
import com.technostack.stream.model.OrderItem;
import com.technostack.stream.model.SampleData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationWeek3 {
    public static void main(String[] args) {

        //groupingBy(category) → Map<Category, List<Item>>

        List<Order> orders = SampleData.orders();
        /**
         * ❌ Wrong thinking
         *
         * “I have a stream, so I call groupingBy on it”
         *
         * ✅ Correct thinking
         *
         * “I have a stream → I must collect it → groupingBy tells HOW to collect”
         *
         * 📌 groupingBy answers this question:
         *
         * “How should elements be organized when the stream ends?”
         */
        Map<String, List<OrderItem>> collect = orders.stream().flatMap(order -> order.getItems().stream()).collect(Collectors.groupingBy(OrderItem::getCategory));
        collect.forEach((key,val) -> System.out.println("key :"+key +" :"+"Val :"+val));

        System.out.println("=================================================================================");


        // groupingBy(brand) + counting() → Map<Brand, Long>

        Map<String, Long> collect1 = orders.stream().flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getBrand, Collectors.counting()));
        System.out.println(collect1);

        System.out.println("=================================================================================");

        //groupingBy(category, averagingDouble(unitPrice))

        Map<String, Double> collect2 = orders.stream().flatMap(order -> order.getItems().stream()).filter(orderItem -> orderItem.getUnitPrice() > 0)
                .collect(Collectors.groupingBy(OrderItem::getCategory, Collectors.averagingDouble(OrderItem::getUnitPrice)));
        System.out.println(collect2);

        System.out.println("=================================================================================");


        //groupingBy(customerId) on orders [Done]

        Map<String, List<Order>> collect3 = orders.stream().collect(Collectors.groupingBy(Order::getCustomerId));
        System.out.println(collect3);

        System.out.println("=================================================================================");

        // groupingBy(status) + mapping(orderId, toList())

        /**
         * 🧠 One-Line Mental Formula (Remember This)
         * groupingBy = HOW to group
         * mapping    = WHAT to keep
         * toList     = WHERE to store
         */
        Map<String, List<String>> collect4 = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.mapping(Order::getId, Collectors.toList())));

        collect4.forEach((status,orderId)-> {
                            if(status == "DELIVERED"){
                                processDelivered(orderId);
                            }else if(status == "PENDING"){
                                processPending(orderId);
                            }
                        });
        System.out.println(collect4);

        System.out.println("=================================================================================");

        // groupingBy(category) then values().stream().flatMap(List::stream) flatten back

       orders.stream().flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getCategory)).values().stream().flatMap(List::stream).forEach(System.out::println);

        System.out.println("=================================================================================");

       // groupingBy(category) then sort each group (map each entry to sorted list)

        Map<String, List<OrderItem>> collect5 = orders.stream().flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getCategory,Collectors.collectingAndThen(Collectors.toList(),list->{
                    list.sort(Comparator.comparing(OrderItem::getUnitPrice));
                    return list;
                }
                )));

        collect5.forEach((Key,val)->{
            System.out.println("Key :"+Key);
            for(OrderItem item : val){
                System.out.println(item.toString());
            }
        });

        System.out.println("=================================================================================");

        // groupingBy(category) then take top 2 per group

        Map<String, List<OrderItem>> result =
                orders.stream()
                        .flatMap(order -> order.getItems().stream())
                        .collect(Collectors.groupingBy(
                                OrderItem::getCategory,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.comparing(OrderItem::getUnitPrice).reversed())
                                                .limit(2)
                                                .collect(Collectors.toList())
                                )
                        ));
        System.out.println(result);
    }

    private static void processPending(List<String> orderid) {
        System.out.println("Pending status processed");
    }

    private static void processDelivered(List<String> orderId) {
        for(String id : orderId){
            System.out.println("Delivered status processed :"+ id);
        }
    }
}
