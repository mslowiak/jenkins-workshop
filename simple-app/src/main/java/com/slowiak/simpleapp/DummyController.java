package com.slowiak.simpleapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping("/hello")
    public String sayMyName(@RequestParam String name) {
        return "Hi! " + name;
    }

}
