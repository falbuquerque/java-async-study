package br.com.falbuquerque.async.parallel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.util.StopWatch;

class ParallelismTest {

    public static void main(String[] args) {
        final ParallelismTest test = new ParallelismTest();
        final Collection<? extends Runnable> tasks = createTasks(10);
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start("sync");
        test.sync(tasks);
        stopWatch.stop();

        stopWatch.start("async");
        test.async(tasks);
        stopWatch.stop();

        stopWatch.start("parallelStream");
        test.parallelStream(tasks);
        stopWatch.stop();

        System.out.println(stopWatch);
    }

    private static Collection<WeatherTask> createTasks(final int iterations) {
        final Collection<WeatherTask> tasks = new ArrayList<WeatherTask>(iterations);

        for (int count = 0; count < iterations; count++) {
            tasks.add(new WeatherTask());
        }

        return tasks;
    }

    private void sync(final Collection<? extends Runnable> tasks) {
        tasks.forEach(task -> task.run());
    }

    private void async(final Collection<? extends Runnable> tasks) {
        final ExecutorService executorService = Executors.newWorkStealingPool();
        final Collection<Future<?>> futures = tasks.stream().map(task -> executorService.submit(task))
                .collect(Collectors.toList());

        futures.forEach(future -> {

            try {
                future.get();
            } catch (final InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        });
    }

    private void parallelStream(final Collection<? extends Runnable> tasks) {
        tasks.stream().parallel().forEach(task -> task.run());
    }

}
