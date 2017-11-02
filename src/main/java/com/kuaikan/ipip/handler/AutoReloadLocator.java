package com.kuaikan.ipip.handler;


import com.kuaikan.ipip.parser.IP;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * @author liguochao.
 */
@Slf4j
public final class AutoReloadLocator implements Observer {

    private final String filePath;
    private final SimpleFileWatchService watchService;

    public AutoReloadLocator() {
        this.filePath = ServerProvider.getFilePath();
        IP.load(filePath);
        this.watchService = new SimpleFileWatchService(filePath, ServerProvider.getReloadInterval());
        this.watchService.addObserver(this);
        this.watchService.execute();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            IP.load(filePath);
            log.info("ip地址库有变化，已重新加载完毕!");
        } catch (Exception e) {
            log.error("加载ip地址库文件异常", e);
            shutdown();
        }
    }

    private void shutdown() {
        watchService.deleteObserver(this);
    }
}
