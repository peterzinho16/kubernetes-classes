package com.bindord.podservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/svc")
public class MainController {

    @GetMapping("/gen/id")
    public Mono<String> generateId() {
        String uuid = UUID.randomUUID().toString();
//        File file = new File("C:\\tmp\\logs\\test\\"+uuid);
//        boolean result = file.mkdir();
//        System.out.println("result: " + result);

        return Mono.just(UUID.randomUUID().toString());
    }
}
