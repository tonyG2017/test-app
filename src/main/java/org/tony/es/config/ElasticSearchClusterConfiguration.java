package org.tony.es.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "es")
@Getter
@Setter
public class ElasticSearchClusterConfiguration {
   private String host;
   private String port;
}
