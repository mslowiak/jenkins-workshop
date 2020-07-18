package com.slowiak.simplebackend;

import com.slowiak.simplebackend.controller.Connector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SimpleBackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SimpleBackendApplication.class, args);

        try (context) {
            Connector connector = context.getBean(Connector.class);
            String productName = context.getEnvironment().getProperty("name");
            String returnedMessage = connector.getMessage(productName)
                    .block();
            log.info(returnedMessage);
        }
    }

}
