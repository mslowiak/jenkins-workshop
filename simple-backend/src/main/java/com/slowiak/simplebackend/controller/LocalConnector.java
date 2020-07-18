package com.slowiak.simplebackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.slowiak.simplebackend.config.Profiles.LOCAL;

@Slf4j
@Component
@Profile(LOCAL)
@RequiredArgsConstructor
public class LocalConnector implements Connector {
    @Override
    public Mono<String> getMessage(String name) {
        log.info("Running with default profile. User DEV or CUSTOM instead.");
        return Mono.just("SIMPLE RESPONSE");
    }
}
