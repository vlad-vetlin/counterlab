package ru.sbt.edu.concurrency.counter;

public class MutexCounter implements Counter {
    private volatile long count;

    @Override
    public synchronized void increment() {
        ++count;
    }

    @Override
    public long getValue() {
        return count;
    }
}
