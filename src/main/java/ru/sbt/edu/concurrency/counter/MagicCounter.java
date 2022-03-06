package ru.sbt.edu.concurrency.counter;

public class MagicCounter implements Counter {
    private final int[] counters;

    private volatile boolean getInProcess = false;

    public MagicCounter(int threadCount) {
        counters = new int[threadCount];
    }
    private int getThreadNumber() {
        return Integer.parseInt(Thread.currentThread().getName());
    }

    @Override
    public void increment() {
        while (getInProcess) {}

        int threadId = getThreadNumber();
        ++counters[threadId];
    }

    @Override
    public long getValue() {
        getInProcess = true;

        int sum = 0;

        for (int counter : counters) {
            sum += counter;
        }

        getInProcess = false;

        return sum;
    }
}
