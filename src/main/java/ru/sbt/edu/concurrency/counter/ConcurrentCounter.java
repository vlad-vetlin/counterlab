package ru.sbt.edu.concurrency.counter;

import java.util.concurrent.Semaphore;

public class ConcurrentCounter implements Counter {
    private final Semaphore semaphore;
    private long count;

    public ConcurrentCounter() {
        semaphore = new Semaphore(1);
    }

    @Override
    public void increment() {
        try {
            semaphore.acquire();

            ++count;

            semaphore.release();

        } catch (InterruptedException exception) {
            System.out.println("Interrupted exception");
        }
    }

    @Override
    public long getValue() {
        return count;
    }
}
