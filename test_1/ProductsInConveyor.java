package com.proftelran.org.homework_20.test_1;

import java.util.concurrent.CyclicBarrier;

public class ProductsInConveyor implements Runnable {
    private String product;
    private int productNumber;
    private CyclicBarrier cyclicBarrier;

    public ProductsInConveyor(String product, int productNumber, CyclicBarrier cyclicBarrier) {
        this.product = product;
        this.productNumber = productNumber;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("-> " + product + " ожидает погрузки на конвейер");
        try {
            cyclicBarrier.await();
            System.out.println("<- " + product + " уже на складе");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}