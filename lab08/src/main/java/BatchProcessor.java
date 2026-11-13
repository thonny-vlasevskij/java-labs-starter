package edu.course.lab08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class BatchProcessor<T, R> {

    public List<TaskResult<R>> processSequentially(
            List<T> inputs,
            TaskFunction<T, R> function) {

        List<TaskResult<R>> results = new ArrayList<>(inputs.size());

        for (int i = 0; i < inputs.size(); i++) {
            T input = inputs.get(i);
            try {
                results.add(TaskResult.success(i, function.apply(input)));
            } catch (Exception error) {
                results.add(TaskResult.failure(i, error));
            }
        }

        return results;
    }

    public List<TaskResult<R>> processConcurrently(
            List<T> inputs,
            TaskFunction<T, R> function,
            ExecutorService executor) throws InterruptedException {

        List<Future<R>> futures = new ArrayList<>(inputs.size());

        for (T input : inputs) {
            Callable<R> task = () -> function.apply(input);
            futures.add(executor.submit(task));
        }

        List<TaskResult<R>> results = new ArrayList<>(inputs.size());

        try {
            for (int i = 0; i < futures.size(); i++) {
                try {
                    results.add(TaskResult.success(i, futures.get(i).get()));
                } catch (ExecutionException error) {
                    Throwable cause = error.getCause();
                    Exception taskError = cause instanceof Exception
                            ? (Exception) cause
                            : new Exception(cause);
                    results.add(TaskResult.failure(i, taskError));
                }
            }
        } catch (InterruptedException interrupted) {
            for (Future<R> future : futures) {
                future.cancel(true);
            }
            throw interrupted;
        }

        return results;
    }
}
