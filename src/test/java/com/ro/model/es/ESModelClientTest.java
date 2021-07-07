package com.ro.model.es;

import com.ro.prop.AppProp;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ESModelClientTest {

    private String indexName = "index-client";

    @Test
    @DisplayName("Should put a new index")
    public void createIndexRest() throws IOException {
        AppProp.loadConfig();
        ESModel esModel = ESModelRestCalls.getEsModelInstance(AppProp.ELASTIC_SEARCH_URL, AppProp.DOMAIN, AppProp.PORT, AppProp.PROTOCOL);
        int code = esModel.putIndex(this.indexName);
        System.out.println("Code: " + code);
        assertEquals(200, code);
    }
}
