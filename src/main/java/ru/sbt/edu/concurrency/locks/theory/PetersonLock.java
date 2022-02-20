package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

public class PetersonLock implements ILock {
    private final boolean[] flag = new boolean[2];
    private int victim;

    @Override
    public void lock() {

    }


    @Override
    public void unlock() {

    }

}
