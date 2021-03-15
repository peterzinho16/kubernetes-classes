package com.bindord.podservice;

import io.reactivex.Observable;
import io.reactivex.Single;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public class ThatsWhyWePlay {

    public static void main(String[] args) {

        Observable<Integer> daysOfWeekLeft =
                Observable.fromCallable(() -> LocalDateTime.now().getDayOfWeek().getValue())
                        .flatMap(day -> Observable.range(day, 7 - day));
        daysOfWeekLeft.forEach(System.out::println);

        Observable<Integer> daysOfWeekLeftDefer =
                Observable.defer(() ->
                        Observable.range(LocalDateTime.now().getDayOfWeek().getValue(), 7 - LocalDateTime.now().getDayOfWeek().getValue()));

        System.out.println("********");
        daysOfWeekLeftDefer.forEach(System.out::println);


    }

}
