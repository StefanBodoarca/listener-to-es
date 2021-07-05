package com.ro.controller;

import com.ro.controller.listener.ExtensionListener;
import com.ro.controller.reader.QueueReader;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadController {
    private LinkedBlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
    private ExtensionListener extensionListener = null;
    private QueueReader queueReader = null;

    public ThreadController() {
        this.extensionListener = new ExtensionListener(this.messageQueue);
        this.queueReader = new QueueReader(this.messageQueue);
    }

    public void startThreads() {
        new Thread(this.extensionListener).start();
    }
}
