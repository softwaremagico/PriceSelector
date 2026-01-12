package com.test.bcnc.infrastructure.price;

import com.test.bcnc.logger.PriceLogger;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;


/***
 * Starts and run the application using Spring Boot.
 */
@OpenAPIDefinition(servers = {@Server(url = "${server.servlet.context-path}", description = "Default Server URL")})
@ComponentScan({"com.test.bcnc"})
@EnableJpaRepositories({"com.test.bcnc.infrastructure"})
@SpringBootApplication
public class PriceSelectorServer {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(PriceSelectorServer.class, args);
    }

    @Bean
    public ApplicationListener<ContextRefreshedEvent> startupLoggingListener() {
        return event -> PriceLogger.info(PriceSelectorServer.class, "### Server started ###");
    }
}
