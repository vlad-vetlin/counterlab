package ru.sbt.edu.concurrency.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter {
    private volatile long counter;
    Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        ++counter;
        lock.unlock();
    }

    @Override
    public long getValue() {
        return counter;
    }
}
