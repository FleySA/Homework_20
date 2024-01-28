package com.proftelran.org.homework_20.test_1;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

class Conveyor implements Runnable {
    private AtomicInteger currentIndex;
    private String[] productName;
    private CyclicBarrier cyclicBarrier;

    public Conveyor(String[] productName, CyclicBarrier cyclicBarrier) {
        this.currentIndex = new AtomicInteger(0);
        this.productName = productName;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("На Конвейер поступило 10 товаров и они доставляются на склад ");

            // Очистка списка товаров
            for (int i = 0; i < productName.length; i++) {
                productName[i] = null;
            }

            System.out.println("Склад опустел");

            // Последний грузчик дает отмашку конвейеру
            currentIndex.set(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}