/*
package org.example;

import java.util.concurrent.Callable;

public class SunRevenue implements Callable<Integer> {
    private Store myStore;

    public SunRevenue(Store myStore) {
        this.myStore = myStore;
    }

    @Override
    public Integer call() throws Exception {
        int taxSum = 0;
        for (int i = 0; i < Store.numberOfItemsSold; i++)
            taxSum += myStore.storeRevenue.get(i);

        return taxSum;
    }
}
*/
