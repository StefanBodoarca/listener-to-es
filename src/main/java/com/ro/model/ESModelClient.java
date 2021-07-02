//implemented as singleton

package com.ro.model;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

public class ESModelClient extends ESModel {
    private static Logger logger = LogManager.getLogger(ESModelClient.class);
    private static ESModelClient esModelClientInsance = null;
    private RestHighLevelClient restHighLevelClient = null;

    public static ESModelClient getESModelClientInstance(String server_url, String domain, int port, String protocol) {
        if(esModelClientInsance == null) {
            return new ESModelClient(server_url, domain, port, protocol);
        }

        return esModelClientInsance;
    }

    private ESModelClient(String server_url, String domain, int port, String protocol){
        super(server_url, domain, port, protocol);
        this.restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(this.DOMAIN, this.PORT, this.PROTOCOL)
                )
        );
    }

    @Override
    public int putIndex(String indexName) {
        try {
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            CreateIndexResponse createIndexResponse = this.restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();

            if(acknowledged) {
                return 200;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return -1;
    }


}
