package com.ro.model.es;

public abstract class ESModel {
    protected String SERVER_URL = "";
    protected String DOMAIN = "";
    protected int PORT = -1;
    protected String PROTOCOL = "";

    public ESModel(String server_url, String domain, int port, String protocol) {
        this.SERVER_URL = server_url;
        this.DOMAIN = domain;
        this.PORT = port;
        this.PROTOCOL = protocol;
    }
    abstract public int putIndex(String indexName);
    abstract public int postDoc(String indexName, String jsonDoc, String docID);
}
