package com.bindord.podgateway.controller;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@RestController
@RequestMapping("/public")
public class PublicController {

    public static final String GEN_ID_ENDPOINT = "/svc/gen/id";

    @Value("${bindord.hosts.id-svc}")
    private String idSvcHost;

    @GetMapping("/get/id")
    public Mono<String> generateId() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        return WebClient.builder().baseUrl(idSvcHost + GEN_ID_ENDPOINT)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build()
                .get()
                .retrieve().bodyToMono(String.class);
    }

    @GetMapping("/get/configmap-value")
    public Mono<String> getConfigMapVal() {
        return Mono.just(idSvcHost);
    }
}
