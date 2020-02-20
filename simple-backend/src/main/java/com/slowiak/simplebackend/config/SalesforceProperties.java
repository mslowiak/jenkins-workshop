package com.slowiak.simplebackend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("salesforce")
public class SalesforceProperties {
    private String url;
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
}
