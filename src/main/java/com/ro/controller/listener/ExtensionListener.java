package com.ro.controller.listener;

import com.ro.controller.reader.MessageReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

public class ExtensionListener implements Runnable {
    private static final Logger logger = LogManager.getLogger(ExtensionListener.class);
    private LinkedBlockingQueue messageQueue = null;
    private MessageReader reader = null;

    public ExtensionListener(LinkedBlockingQueue messageQueue) {
        this.messageQueue = messageQueue;
        reader = new MessageReader();
    }

    @Override
    public void run() {
        while(true) {
            //read messages and send for processing
            try {
                String message = reader.readMessage();
                logger.info("message", message);
                Thread.sleep(100);
            } catch (InterruptedException iex) {
                logger.error(iex.getMessage(), iex);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}
