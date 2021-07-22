package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;


public class App {
    public static void main(String[] args) {
        Store store1 = new Store("Магазин 1");
        Store store3 = new Store("Магазин 3");
        Store store2 = new Store("Магазин 2");

        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder tax = new LongAdder();

        List<Thread> createStoreRevenueThread = new ArrayList<>();
        createStoreRevenueThread.add(new Thread(null, store1::createStoreRevenue, "Store1_StoreRevenue"));
        createStoreRevenueThread.add(new Thread(null, store2::createStoreRevenue, "Store2_StoreRevenue"));
        createStoreRevenueThread.add(new Thread(null, store3::createStoreRevenue, "Store3_StoreRevenue"));

        createStoreRevenueThread.stream()
                .forEach(Thread::start);
        System.out.println(store1.storeRevenue);
        System.out.println(store3.storeRevenue);
        System.out.println(store2.storeRevenue);


        List<Callable<Integer>> myCallableList = new ArrayList<>();
        myCallableList.add(store1::call);
        myCallableList.add(store2::call);
        myCallableList.add(store3::call);

        /*myCallableList.stream()
                .forEach(task -> es.submit(tax.add((long)task))); //не пойму как передать Integer? */

        


        List<Integer> totalTaxList =











    }

}





