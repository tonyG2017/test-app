package org.tony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
//https://www.tutorialspoint.com/spring_boot/spring_boot_enabling_swagger2.htm
public class TestApp {
    public static void main(String[] args) {
        SpringApplication.run(TestApp.class);
    }
    @Bean
    public Docket testAppApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.tony"))
                .build()
                .genericModelSubstitutes(Optional.class); //To make Java 8 Optional @RequestParam  show in the sawgger ui, we have to add ".genericModelSubstitutes( Optional.class )" to the Docket-Bean creation.
                //https://github.com/springfox/springfox/issues/1848
    }
}
