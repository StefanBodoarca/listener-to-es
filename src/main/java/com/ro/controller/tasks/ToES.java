package com.ro.controller.tasks;

import com.ro.model.es.ESModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToES implements Runnable {
    private static final Logger logger = LogManager.getLogger(ToES.class);
    private ESModel esModel = null;

    public ToES(ESModel esModel){
        this.esModel = esModel;
    }
    @Override
    public void run() {}

}
