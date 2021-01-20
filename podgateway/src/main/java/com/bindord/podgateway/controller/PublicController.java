package com.bindord.podgateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/public")
public class PublicController {

    public static final String GEN_ID_ENDPOINT = "/svc/gen/id";

    @Value("${bindord.hosts.id-svc}")
    private String idSvcHost;

    @GetMapping("/get/id")
    public Mono<String> generateId() {
        return WebClient.builder().baseUrl(idSvcHost + GEN_ID_ENDPOINT)
                .build()
                .get()
                .retrieve().bodyToMono(String.class);
    }

    @GetMapping("/get/configmap-value")
    public Mono<String> getConfigMapVal() {
        return Mono.just(idSvcHost);
    }
}
