package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;


public class LockOne implements ILock {
    private final boolean[] flag = new boolean[2];

    private int getThreadNumber() {
        return Integer.parseInt(Thread.currentThread().getName());
    }

    @Override
    public void lock() {
        int number = getThreadNumber();
        int secondNumber = 1 - number;

        flag[number] = true;
        while (flag[secondNumber]) {}
    }


    @Override
    public void unlock() {
        int number = getThreadNumber();
        flag[number] = false;
    }
}