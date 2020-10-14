package org.tony.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    private final ElasticSearchClusterConfiguration elasticSearchClusterConfiguration;

    @Autowired

    RestClientConfig(ElasticSearchClusterConfiguration elasticSearchClusterConfiguration){
        this.elasticSearchClusterConfiguration=elasticSearchClusterConfiguration;
    }

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        String hostAndPort=String.format("%s:%s",elasticSearchClusterConfiguration.getHost(),elasticSearchClusterConfiguration.getPort());
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
