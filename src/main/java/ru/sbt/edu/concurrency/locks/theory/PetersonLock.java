package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;

public class PetersonLock implements ILock {
    private final boolean[] flag = new boolean[2];
    private volatile int victim;

    private int getThreadNumber() {
        return Integer.parseInt(Thread.currentThread().getName());
    }

    @Override
    public void lock() {
        int threadId = getThreadNumber();
        int secondThreadId = 1 - threadId;

        flag[threadId] = true;
        victim = threadId;

        while (flag[secondThreadId] && victim == threadId) {}
    }


    @Override
    public void unlock() {
        int threadId = getThreadNumber();
        flag[threadId] = false;
    }

}
