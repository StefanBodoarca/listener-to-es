package com.ro.controller.tasks;

import com.ro.model.es.ESModel;
import com.ro.prop.AppProp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ToES extends CommonES implements Runnable {
    private static final Logger logger = LogManager.getLogger(ToES.class);
    private ESModel esModel;

    public ToES(ESModel esModel) {
        this.esModel = esModel;
    }

    @Override
    public void run() {
        String document = this.jsonDocument;
        if(document.length() > 0) {
            JSONObject obj = new JSONObject(document);
            String _doc = obj.getString("_doc");
            String _index_name = obj.getString("_index_name");
            String _doc_id = obj.getString("_doc_id");
            int code = this.esModel.postDoc(_index_name, _doc, _doc_id);
            if(AppProp.CHROME_DEBUG_ENABLED) {
                System.err.println("Response code form ES: " + code);
            }
        }
    }
}
