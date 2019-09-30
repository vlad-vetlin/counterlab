import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.*;

public class CounterTest {
    private static final Counter counter = new SeqCounter();

    @Test
    public void testSequentialExecution() throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(1);

        int incrementCallsCount = 11;

        List<? extends Future<?>> futures = range(0, incrementCallsCount)
                .mapToObj(i -> executors.submit(incrementRunnable()))
                .collect(toList());
        for (Future<?> future : futures) {
            future.get();
        }
        assertEquals("Oops! Smth is wrong!", incrementCallsCount, counter.getValue());
    }

    private static Runnable incrementRunnable(){
        return counter::increment;
    }
}