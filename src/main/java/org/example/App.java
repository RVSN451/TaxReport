package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;


public class App {
    public static final int NUMBER_OF_ITEMS_SOLD  = 5; // Количество покупок в магазине за 1 день
    public static final int MAX_CHECK = 1000; //Максимальная стоимость одной покупки

    public static void main(String[] args) {

        LongAdder tax = new LongAdder();
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Store> myStores = new ArrayList<>();
        myStores.add(new Store("Магазин 1", tax));
        myStores.add(new Store("Магазин 2", tax));
        myStores.add(new Store("Магазин 3", tax));
        myStores.add(new Store("Магазин 4", tax));


        List<Thread> createStoreRevenueThread = new ArrayList<>();
        for (Store store : myStores) {
            createStoreRevenueThread.add(new Thread(null, store::createStoreRevenue));
        }

        createStoreRevenueThread.stream()
                .forEach(Thread::start);

        for (Thread thread:createStoreRevenueThread){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Store store : myStores) {
            es.submit(store);
        }

        try {
            es.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdown();

        System.out.printf("Общая выручка %d магазинов равна - %d", myStores.size(), tax.sum());

    }
}





