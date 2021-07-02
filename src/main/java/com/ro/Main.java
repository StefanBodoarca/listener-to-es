package com.ro;

import com.ro.model.ESModel;
import com.ro.model.ESModelClient;
import com.ro.model.ESModelRestCalls;
import com.ro.prop.AppProp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            AppProp.loadConfig();
            //createIndexRest(AppProp.ELASTIC_SEARCH_URL, AppProp.DOMAIN, AppProp.PORT, AppProp.PROTOCOL, "index-rest");
            createIndexClient(AppProp.ELASTIC_SEARCH_URL, AppProp.DOMAIN, AppProp.PORT, AppProp.PROTOCOL, "index-client");

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            System.out.println("App Closed");
        }
    }

    private static void createIndexRest(String ELASTIC_SEARCH_URL, String DOMAIN, int PORT, String PROTOCOL, String indexName) {
        ESModel esModel = ESModelRestCalls.getEsModelInstance(ELASTIC_SEARCH_URL, DOMAIN, PORT, PROTOCOL);
        System.out.println("Response code");
        System.out.println(esModel.putIndex(indexName));
    }

    private static void createIndexClient(String ELASTIC_SEARCH_URL, String DOMAIN, int PORT, String PROTOCOL, String indexName) {
        ESModel esModel = ESModelClient.getESModelClientInstance(ELASTIC_SEARCH_URL, DOMAIN, PORT, PROTOCOL);
        System.out.println("Response code");
        System.out.println(esModel.putIndex(indexName));
    }
}
