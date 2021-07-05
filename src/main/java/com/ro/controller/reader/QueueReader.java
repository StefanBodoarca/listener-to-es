package com.ro.controller.reader;

import com.ro.controller.listener.ExtensionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueReader implements Runnable {
    private static final Logger logger = LogManager.getLogger(QueueReader.class);
    private LinkedBlockingQueue messageQueue = null;

    public QueueReader(LinkedBlockingQueue messageQueue) {
        this.messageQueue = messageQueue;

    }
    @Override
    public void run() {
        while (true) {
            //take message and process
        }
    }
}
