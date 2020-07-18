package com.slowiak.simplebackend.controller;

import reactor.core.publisher.Mono;

public interface Connector {
    Mono<String> getMessage(String name);
}
