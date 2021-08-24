package com.eljaiek.github.playground;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;

/**
 * Handles <code>FooResource</code> requests.
 */
@Component
class FooRequestHandler {

    private static final FooResource FOO_EVENT = new FooResource(1, "Foo Fu");

    /**
     * Returns one <code>FooResource</code> every second.
     *
     * @return one <code>FooResource</code> every second
     */
    Mono<ServerResponse> getFoo() {
        return ServerResponse.ok().contentType(TEXT_EVENT_STREAM).body(streamFoo(), FooResource.class);
    }

    private static Flux<FooResource> streamFoo() {
        Flux<FooResource> events = Flux.fromStream(Stream.generate(() -> FOO_EVENT));
        var interval = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(events, interval, (foo, x) -> foo);
    }
}
