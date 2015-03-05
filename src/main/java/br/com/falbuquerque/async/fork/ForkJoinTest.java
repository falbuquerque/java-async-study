package br.com.falbuquerque.async.fork;

import java.util.concurrent.ForkJoinPool;

import org.springframework.util.StopWatch;

class ForkJoinTest {

    public static void main(String[] args) {
        final StopWatch stopWatch = new StopWatch();
        final Long n = 45L;

        stopWatch.start("nonRecursive"); // best solution :)
        System.out.println(new Fibonacci().solve(n));
        stopWatch.stop();

        stopWatch.start("sequential");
        System.out.println(new Fibonacci().recursiveSolve(n));
        stopWatch.stop();

        final Long threshold = 30L; // the threshold matters; it must be adjusted
        stopWatch.start("forkJoin (" + threshold + ")");
        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        final FibonacciTask task = new FibonacciTask(n, threshold);
        System.out.println(forkJoinPool.invoke(task));
        stopWatch.stop();

        System.out.println(stopWatch);
    }

}
