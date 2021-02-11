package io.projectreactor.szydra;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class KafkaConsumer {

    void acceptWithMap(Flux<String> input) {
        input.doOnNext(message -> System.out.println("Received message: " + message))
                .flatMap(message -> Mono.just(message)
                        .map(Integer::valueOf)
                        .doOnSuccess(x -> System.out.println("Processed successfully: " + message))
                        .onErrorMap(IllegalArgumentException::new)
                )
                .onErrorContinue((throwable, o) -> System.out.println("Error: " + throwable.getClass()))
                .subscribe();
    }

    void acceptWithFlatMap(Flux<String> input) {
        input.doOnNext(message -> System.out.println("Received message: " + message))
                .flatMap(message -> Mono.just(message)
                        .flatMap(x -> Mono.just(Integer.valueOf(x)))
                        .doOnSuccess(x -> System.out.println("Processed successfully: " + message))
                        .onErrorMap(IllegalArgumentException::new)
                )
                .onErrorContinue((throwable, o) -> System.out.println("Error: " + throwable.getClass()))
                .subscribe();
    }

    void acceptWithoutOnErrorContinue(Flux<String> input) {
        input.doOnNext(message -> System.out.println("Received message: " + message))
                .flatMap(message -> Mono.just(message)
                        .map(Integer::valueOf)
                        .doOnSuccess(x -> System.out.println("Processed successfully: " + message))
                        .onErrorResume(throwable -> {
                            System.out.println("Error: " + throwable.getClass());
                            return Mono.empty();
                        })
                )
                .subscribe();
    }
}
