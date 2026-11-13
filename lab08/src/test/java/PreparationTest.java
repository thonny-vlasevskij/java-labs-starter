import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreparationTest {

    @Test
    void callablesReturnValuesInSubmissionOrder() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            List<Callable<Integer>> tasks = List.of(
                    () -> 10,
                    () -> 20,
                    () -> 30
            );

            List<Future<Integer>> futures = new ArrayList<>();
            for (Callable<Integer> task : tasks) {
                futures.add(executor.submit(task));
            }

            List<Integer> results = new ArrayList<>();
            for (Future<Integer> future : futures) {
                results.add(future.get());
            }

            assertEquals(List.of(10, 20, 30), results);
        } finally {
            executor.shutdown();
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        }
    }
}
