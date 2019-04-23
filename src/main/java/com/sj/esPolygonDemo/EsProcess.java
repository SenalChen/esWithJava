package com.sj.esPolygonDemo;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @Author: Chen Sijia
 * @Date: 2019/4/22 23:23
 */
@Component
public class EsProcess {

    private ElasticsearchTemplate elasticsearchTemplate;

    private Client client;

    @Autowired
    public void set(ElasticsearchTemplate elasticsearchTemplate) {

        if (client == null) {
            client = elasticsearchTemplate.getClient();
        }
    }

    public IndexResponse createIndex(String indexName, String typeName, String json) throws IOException {
        IndexResponse response = client.prepareIndex(indexName, typeName)
                .setSource(json, XContentType.JSON)
                .get();
        return response;
    }

    public IndexResponse createIndex(String indexName, String typeName,
                                     String id, XContentBuilder doc) {
        return client.prepareIndex(indexName, typeName, id)
                .setSource(doc).get();
    }

    /**
     *
     * @param map
     * @return
     * @throws IOException
     */
    public XContentBuilder createDoc(Map<String, Object> map) throws IOException {
        XContentBuilder builder = jsonBuilder().startObject();
        map.forEach((x, y) -> {
            try {
                builder.field(x, y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        builder.endObject();
        return builder;
    }

}
