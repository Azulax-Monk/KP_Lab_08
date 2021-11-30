package com.example.lab_08.classes;

import java.util.concurrent.*;

import static java.lang.Thread.MAX_PRIORITY;

public final class ThreadPool {
    private static ThreadPool instance;
    private ExecutorService executorService;

    private ThreadPool() {
        var boundedQueue = new ArrayBlockingQueue<Runnable>(50);
        executorService = new ThreadPoolExecutor(20, 20, 60, TimeUnit.SECONDS,
                boundedQueue, new ThreadPoolExecutor.AbortPolicy());
    }

    public void executeRunnable(Runnable task) {
        executorService.submit(task);
    }

    public static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }

    public void cleanUp() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(MAX_PRIORITY, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            System.out.println("Exception while waiting for thread pool to close");
        }
    }
}
