package edu.course.lab08;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BatchProcessorTest {

    private final BatchProcessor<Integer, Integer> processor = new BatchProcessor<>();

    @Test
    void sequentialReturnsResultsInInputOrder() {
        List<TaskResult<Integer>> results =
                processor.processSequentially(List.of(3, 1, 2), value -> value * 10);

        assertEquals(List.of(30, 10, 20),
                results.stream().map(TaskResult::value).toList());
        assertEquals(List.of(0, 1, 2),
                results.stream().map(TaskResult::index).toList());
        assertTrue(results.stream().allMatch(TaskResult::isSuccess));
    }

    @Test
    void sequentialKeepsFailureAndContinues() {
        List<TaskResult<Integer>> results = processor.processSequentially(
                List.of(1, 0, 2),
                value -> {
                    if (value == 0) {
                        throw new IllegalArgumentException("zero is invalid");
                    }
                    return 10 / value;
                });

        assertTrue(results.get(0).isSuccess());
        assertFalse(results.get(1).isSuccess());
        assertInstanceOf(IllegalArgumentException.class, results.get(1).error());
        assertTrue(results.get(2).isSuccess());
        assertEquals(1, results.get(2).value());
    }

    @Test
    void emptyInputReturnsEmptyList() {
        assertTrue(processor.processSequentially(List.of(), value -> value).isEmpty());

        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            assertTrue(processor.processConcurrently(
                    List.<Integer>of(), value -> value, executor).isEmpty());
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            fail("Test was interrupted", error);
        } finally {
            shutdown(executor);
        }
    }

    @Test
    void concurrentReturnsResultsInInputOrder() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            List<TaskResult<Integer>> results = processor.processConcurrently(
                    List.of(1, 2, 3),
                    value -> {
                        if (value == 1) {
                            Thread.yield();
                        }
                        return value * 10;
                    },
                    executor);

            assertEquals(List.of(10, 20, 30),
                    results.stream().map(TaskResult::value).toList());
            assertEquals(List.of(0, 1, 2),
                    results.stream().map(TaskResult::index).toList());
        } finally {
            shutdown(executor);
        }
    }

    @Test
    void concurrentKeepsFailureAndOtherResults() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            List<TaskResult<Integer>> results = processor.processConcurrently(
                    List.of(2, 0, 4),
                    value -> {
                        if (value == 0) {
                            throw new IllegalStateException("bad input");
                        }
                        return 100 / value;
                    },
                    executor);

            assertEquals(50, results.get(0).value());
            assertFalse(results.get(1).isSuccess());
            assertInstanceOf(IllegalStateException.class, results.get(1).error());
            assertEquals(25, results.get(2).value());
        } finally {
            shutdown(executor);
        }
    }

    @Test
    void inputListIsNotChanged() throws InterruptedException {
        List<Integer> input = new ArrayList<>(List.of(4, 5, 6));
        List<Integer> original = new ArrayList<>(input);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            processor.processSequentially(input, value -> value + 1);
            processor.processConcurrently(input, value -> value + 1, executor);
            assertEquals(original, input);
        } finally {
            shutdown(executor);
        }
    }

    @Test
    void processorDoesNotShutdownCallerExecutor() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            processor.processConcurrently(List.of(1, 2), value -> value, executor);
            assertFalse(executor.isShutdown());
        } finally {
            shutdown(executor);
        }
    }

    private static void shutdown(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException error) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
