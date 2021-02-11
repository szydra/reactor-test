package io.projectreactor.szydra;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

class KafkaConsumerTest {

    private final KafkaConsumer kafkaConsumer = new KafkaConsumer();

    @Test
    void testWithMap() {
        kafkaConsumer.acceptWithMap(Flux.fromStream(Stream.of("1", "2", "three", "4", "5")));
    }

    @Test
    void testWithFlatMap() {
        kafkaConsumer.acceptWithFlatMap(Flux.fromStream(Stream.of("1", "2", "three", "4", "5")));
    }

    @Test
    void testWithoutOnErrorContinue() {
        kafkaConsumer.acceptWithoutOnErrorContinue(Flux.fromStream(Stream.of("1", "2", "three", "4", "5")));
    }
}
