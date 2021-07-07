package com.ro.controller.tasks;

public class CommonES {
    protected String jsonDocument;

    protected CommonES(){
        this.jsonDocument = null;
    }

    public String getJsonDocument() {
        return jsonDocument;
    }

    public void setJsonDocument(String jsonDocument) {
        this.jsonDocument = jsonDocument;
    }
}
