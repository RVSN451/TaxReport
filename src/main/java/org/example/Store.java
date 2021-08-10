package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Store implements Runnable {
    public static final int numberOfItemsSold = 8;
    private static Random random = new Random();

    private LongAdder tax;
    private String nameStore;
    private List<Integer> storeRevenue;


    public Store(String nameStore, LongAdder tax){
        this.nameStore = nameStore;
        this.storeRevenue = new ArrayList<>(numberOfItemsSold);
        this.tax = tax;

    }

    public void createStoreRevenue(){
        for (int i = 0; i < numberOfItemsSold; i++) {
            storeRevenue.add(i, random.nextInt(App.MAX_CHECK));
        }
        System.out.println(nameStore  + " -  дневная выручка:" + storeRevenue);
    }


    @Override
    public void run() {
        for (Integer i : storeRevenue){
            tax.add(i);
        }
    }
}
