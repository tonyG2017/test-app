package org.tony.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.IndicesRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

@Service
@Slf4j
public class SimpleElasticService {
    private  final RestHighLevelClient highLevelClient;

    @Autowired
    SimpleElasticService(RestHighLevelClient highLevelClient){
        this.highLevelClient=highLevelClient;
    }

    public boolean indexExist(String index) {
        boolean isExist = false;
        try {
            IndicesClient indicesClient = highLevelClient.indices();
            GetIndexRequest getIndexRequest = new GetIndexRequest(index);
            isExist = indicesClient.exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return isExist;
    }

    //https://stackoverflow.com/questions/51953617/how-to-get-all-indices-with-elastics-high-level-rest-client
    public String[] catIndices(){
        GetIndexRequest request = new GetIndexRequest("*");
        String[] indices = null;
        try{
            GetIndexResponse response = highLevelClient.indices().get(request, RequestOptions.DEFAULT);
            indices = response.getIndices();
        }catch (IOException e){
            e.printStackTrace();
        }
        return indices;
    }

    public SearchHit[] simpleSearchMatchAll(String index) throws Exception{
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        return searchHits;
    }

    public SearchHit[] simpleSearchTerm(String index, String field, String value) throws Exception{
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        log.info("filed={}, value={}",field,value);
        searchSourceBuilder.query(QueryBuilders.termQuery(field,value));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        return searchHits;
    }

    public SearchHit[] simpleSearchMatch(String index, String field, String value) throws Exception{
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        log.info("filed={}, value={}",field,value);
        searchSourceBuilder.query(QueryBuilders.matchQuery(field,value));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        return searchHits;
    }
}
