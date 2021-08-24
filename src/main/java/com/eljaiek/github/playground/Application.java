package com.eljaiek.github.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Creates the request router for handling <code>FooResource</code> requests.
     *
     * @param requestHandler that will handle each request
     * @return the<code>FooResource</code> requests router
     */
    @Bean
    RouterFunction<ServerResponse> fooRouter(FooRequestHandler requestHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/foo"), sr -> requestHandler.getFoo());
    }
}
