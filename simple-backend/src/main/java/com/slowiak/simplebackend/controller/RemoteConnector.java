package com.slowiak.simplebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.slowiak.simplebackend.config.Profiles.CUSTOM;
import static com.slowiak.simplebackend.config.Profiles.DEV;

@Component
@Profile({CUSTOM, DEV})
@RequiredArgsConstructor
public class RemoteConnector implements Connector {
    private final WebClient webClient;

    @Override
    public Mono<String> getMessage(String name) {
        return webClient.get()
                .uri("/hello?name=" + name)
                .retrieve()
                .bodyToMono(String.class);
    }
}
