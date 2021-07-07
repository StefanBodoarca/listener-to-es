//implemented as singleton

package com.ro.model.es;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

//todo change response codes from 200 in whats predefined in the client and proceed accordingly

public class ESModelClient extends ESModel {
    private static final Logger logger = LogManager.getLogger(ESModelClient.class);
    private static ESModelClient esModelClientInstance = null;
    private RestHighLevelClient restHighLevelClient = null;

    public static ESModelClient getESModelClientInstance(String server_url, String domain, int port, String protocol) {
        if(esModelClientInstance == null) {
            return new ESModelClient(server_url, domain, port, protocol);
        }

        return esModelClientInstance;
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

    @Override
    public int postDoc(String indexName, String jsonDoc, String docID) {
        try {
            IndexRequest request = new IndexRequest(indexName);
            request.id(docID);
            request.source(jsonDoc, XContentType.JSON);
            IndexResponse indexResponse = this.restHighLevelClient.index(request, RequestOptions.DEFAULT);

            String index = indexResponse.getIndex();
            String id = indexResponse.getId();
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                return 200;
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                return 200;
            }
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                return -1;
            }
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure :
                        shardInfo.getFailures()) {
                    String reason = failure.reason();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return -1;
    }
}
