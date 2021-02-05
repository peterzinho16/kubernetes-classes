package com.bindord.podservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class DefaultController {

    @GetMapping("")
    public Mono<String> generateId() {
        return Mono.just("Default endpoint");
    }
}
