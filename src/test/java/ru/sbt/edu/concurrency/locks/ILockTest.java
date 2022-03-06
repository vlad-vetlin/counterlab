package ru.sbt.edu.concurrency.locks;

import org.junit.Test;
import ru.sbt.edu.concurrency.counter.Counter;
import ru.sbt.edu.concurrency.counter.ILockCounter;
import ru.sbt.edu.concurrency.counter.MagicCounter;
import ru.sbt.edu.concurrency.locks.theory.PetersonLock;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

import static junit.framework.TestCase.assertEquals;

public class ILockTest {
    @Test
    public void testTheoryLock()  {
//        ILock lock = new LockOne();
        ILock lock = new PetersonLock();
        Counter counter = new ILockCounter(lock);
        //try: 1, 2, 10, 100, 1000
//        testCounter(counter, 1);
//        testCounter(counter, 2);
//        testCounter(counter, 10);
//        testCounter(counter, 100);
        testCounter(counter, 1000);
    }

    @Test
    public void testNaiveCounter()  {
//        Counter counter = new SeqCounter();
//        Counter counter = new MutexCounter();
//        Counter counter = new LockCounter();
        Counter counter = new MagicCounter(2);

        testCounter(counter, 1000);
    }

    private void testCounter(Counter counter, int iters) {
        Runnable increment = () -> {
            System.out.println(TwoThreadIds.me());
            for (int i = 0; i < iters; i++) {
                counter.increment();
            }
        };

        // имена тредов, чтобы в lockOne было удобно доставать 0 и 1 как индексы (просто в качестве игрушечного примера)
        Thread t0 = new Thread(increment, "0");
        Thread t1 = new Thread(increment, "1");
        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long count = counter.getValue();
        System.out.println(count);
        assertEquals("Oops! Unexpected Behaviour!", iters * 2, count);
    }
}