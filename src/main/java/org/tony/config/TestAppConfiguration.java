package org.tony.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "greeting")
@Getter
@Setter
public class TestAppConfiguration {
   private String template;
   private String defaultName;
}
