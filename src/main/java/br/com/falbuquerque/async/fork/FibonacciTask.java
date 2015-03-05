package br.com.falbuquerque.async.fork;

import java.util.concurrent.RecursiveTask;

class FibonacciTask extends RecursiveTask<Long> {

    private static final long serialVersionUID = 4075296203318390873L;

    private final Fibonacci fibonacci = new Fibonacci();
    private final Long n;
    private final Long threshold;

    FibonacciTask(final Long n, final Long threshold) {
        this.n = n;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {

        if (n <= threshold) {
            return fibonacci.recursiveSolve(n);
        } else {
            final FibonacciTask worker1 = new FibonacciTask(n - 1, threshold);
            final FibonacciTask worker2 = new FibonacciTask(n - 2, threshold);
            worker1.fork();

            return worker2.compute() + worker1.join();
        }

    }

}
