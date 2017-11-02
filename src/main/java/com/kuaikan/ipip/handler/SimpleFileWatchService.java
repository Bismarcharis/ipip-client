package com.kuaikan.ipip.handler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.File;
import java.util.Observable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liguochao.
 */
public class SimpleFileWatchService extends Observable {

    private File file;
    private int intervalSeconds;
    private long lastModified;
    private ScheduledExecutorService scheduler;

    public SimpleFileWatchService(String filePath, int intervalSeconds) {
        this.file = new File(filePath);
        this.intervalSeconds = intervalSeconds;
        this.lastModified = file.lastModified();
        this.scheduler = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("monitor-ipip-reload-%d").build());
    }

    void execute() {
        scheduler.scheduleAtFixedRate(() -> {
            long l = file.lastModified();
            if (l != lastModified) {
                lastModified = l;
                setChanged();
                notifyObservers();
            }
        }, 60, intervalSeconds, TimeUnit.SECONDS);
    }
}
