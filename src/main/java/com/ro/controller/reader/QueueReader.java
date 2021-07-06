package com.ro.controller.reader;

import com.ro.prop.AppProp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class QueueReader implements Runnable {
    private static final Logger logger = LogManager.getLogger(QueueReader.class);
    private BlockingQueue messageQueue = null;

    public QueueReader(BlockingQueue messageQueue) {
        this.messageQueue = messageQueue;

    }

    @Override
    public void run() {
        while (true) {
            //take message and process
            try {
                String jsonMessage = this.messageQueue.take().toString();
                if(AppProp.CHROME_DEBUG_ENABLED) {
                    System.err.println("Message from queue: " + jsonMessage);
                }
                Thread.sleep(100);
            } catch (InterruptedException iex) {
                logger.error(iex.getMessage(), iex);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}
