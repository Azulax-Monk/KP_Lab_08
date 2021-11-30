package com.example.lab_08.classes.threads;

import java.util.ArrayList;
import java.util.concurrent.*;

import static java.lang.Thread.MAX_PRIORITY;

public final class ThreadPool {
    private static ThreadPool instance;
    private ExecutorService executorService;
    private ArrayList<Future<?>> tasks;

    private ThreadPool() {
        tasks = new ArrayList<>();

        var boundedQueue = new ArrayBlockingQueue<Runnable>(50);
        executorService = new ThreadPoolExecutor(20, 20, 60, TimeUnit.SECONDS,
                boundedQueue, new ThreadPoolExecutor.AbortPolicy());
    }

    public void executeRunnable(Runnable task) {
        tasks.add(executorService.submit(task));
    }

    public static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }

    public void cleanUp() {
        for (var t : tasks) {
            t.cancel(true);
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
