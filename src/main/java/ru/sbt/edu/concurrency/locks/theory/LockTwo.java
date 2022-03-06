package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;

public class LockTwo implements ILock {
    private volatile int victim;

    @Override
    public void lock() {
        int threadId = (int)Thread.currentThread().getId();
        victim = threadId;
        while (victim == threadId) {}
    }


    @Override
    public void unlock() {
    }
}
