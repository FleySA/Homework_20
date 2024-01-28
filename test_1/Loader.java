package com.proftelran.org.homework_20.test_1;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Loader implements Runnable {
    private int loaderNumber;
    private CyclicBarrier cyclicBarrier;
    private AtomicInteger currentIndex;
    private AtomicBoolean isEmpty;

    public Loader(int loaderNumber, CyclicBarrier cyclicBarrier, AtomicInteger currentIndex, AtomicBoolean isEmpty) {
        this.loaderNumber = loaderNumber;
        this.cyclicBarrier = cyclicBarrier;
        this.currentIndex = currentIndex;
        this.isEmpty = isEmpty;
    }

    @Override
    public void run() {
        try {
            System.out.println("Грузчик " + loaderNumber + " начал работу");
            cyclicBarrier.await(); // Ждем, пока все товары попадут на склад

            while (currentIndex.get() < 10) {
                int currentProductIndex = currentIndex.getAndIncrement();
                System.out.println("Грузчик " + loaderNumber + " взял товар с позиции " + (currentProductIndex + 1));
                // Обработка товара
            }
            if (currentIndex.get() == 10) {
                isEmpty.set(true);
                System.out.println("Склад опустел");
                cyclicBarrier.await();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}