package org.example;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.LongAdder;

public class Store implements Callable<Integer> {
    public static int numberOfItemsSold = 3;
    private static Random random = new Random();

    protected String nameStore;
    protected AtomicIntegerArray storeRevenue;



    public Store(String nameStore){
        this.nameStore = nameStore;
        this.storeRevenue = new AtomicIntegerArray(numberOfItemsSold);

    }

    public void createStoreRevenue(){
        for (int i = 0; i < numberOfItemsSold; i++) {
            storeRevenue.lazySet(i, random.nextInt(50));
        }
    }

    @Override
    public Integer call() throws Exception {
        int taxSum = 0;
        for(int i = 0; i < numberOfItemsSold; i++)
            taxSum += storeRevenue.get(i);

        return taxSum;
    }
}
