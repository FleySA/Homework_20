package com.proftelran.org.homework_20.test_1;

import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



public class TestConveyor {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество грузчиков:");
        int numberOfLoaders = scanner.nextInt();

        String[] productName = {"Product_1", "Product_2", "Product_3", "Product_4", "Product_5",
                "Product_6", "Product_7", "Product_8", "Product_9", "Product_10"};
        int totalProducts = productName.length;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalProducts + numberOfLoaders);
        AtomicInteger currentIndex = new AtomicInteger(0);
        AtomicBoolean isEmpty = new AtomicBoolean(false);

        // Создание потоков для продуктов на конвейере
        for (int i = 1; i <= totalProducts; i++) {
            new Thread(new ProductsInConveyor(productName[i - 1], i, cyclicBarrier)).start();
            Thread.sleep(500);
        }

        // Создание потоков для грузчиков
        for (int i = 1; i <= numberOfLoaders && i <= totalProducts; i++) {
            new Thread(new Loader(i, cyclicBarrier, currentIndex, isEmpty )).start();
        }

        scanner.close();
    }
}
