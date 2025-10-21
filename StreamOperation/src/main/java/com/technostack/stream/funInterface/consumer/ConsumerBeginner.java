package com.technostack.stream.funInterface.consumer;

import com.technostack.stream.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerBeginner {

    /**
     *
     * Few Points to Focus on Consumer for Revesion
     * ---------------------------------------------
     * Consumer<T>
     * -----------
     * Consumer<T> is used when we have to provide some input parameter, perform certain operation,
     * but don’t need to return anything. Moreover, we can use Consumer to consume object and perform
     * certain operation.
     *
     * ✅ It represents an operation that takes a single input argument and returns no result.
     * ✅ It is used for performing side effects like printing, logging, modifying objects, saving to DB, etc.
     * ✅ It’s part of the package: java.util.function.Consumer.
     *
     * Chaining Method: default Consumer<T> andThen(Consumer<? super T> after)
     *
     * 👉 andThen() lets you chain multiple Consumers in sequence:
     *
     * Consumer<String> c1 = s -> System.out.println("Hello " + s);
     * Consumer<String> c2 = s -> System.out.println("Bye " + s);
     * c1.andThen(c2).accept("Bikash");
     *
     * Output:
     * ======
     * Hello Bikash
     * Bye Bikash
     *
     * So internally:
     * 	•	You pass one parameter (T t)
     * 	•	Each consumer executes accept(t)
     * 	•	You can chain them sequentially with andThen()
     */

    //🌱 Beginner Level
    //	1.	Create a Consumer that prints each element of a list of integers.

    public static void printConsumerData(){
      List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7);
      Consumer<Integer> intConsumer = val -> System.out.println(val);
      integerList.stream().forEach(intConsumer);
    }

    //  2.Create a Consumer that prints each product’s name in uppercase.

    public static void printProductNameInUpperCaseUsingConsumer(List<Product> productList){
        Consumer<Product> nameConsumer = productname -> System.out.println("Product Name :"+ productname.getName().toUpperCase());
        productList.forEach(nameConsumer);
    }

    //  3.Chain two Consumers: one prints a number, another prints its double.

    public static void usingConsumerAndThenMethod(){
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7);
        Consumer<Integer> intConsumer = num -> System.out.println("Print Number : "+ num);
        Consumer<Integer> printSqure = a -> System.out.println("Print Squre :"+ (a * a));

        integerList.stream().forEach(intConsumer.andThen(printSqure));

    }

    //  4.Use Consumer with forEach() to print employee names and salaries.

    
    //  5.Write a Consumer to print "Processing complete" after a list iteration.
}
