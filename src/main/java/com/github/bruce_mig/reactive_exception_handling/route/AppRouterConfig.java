package com.github.bruce_mig.reactive_exception_handling.route;

import com.github.bruce_mig.reactive_exception_handling.handler.BookHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AppRouterConfig {

    private final BookHandler bookHandler;

    public AppRouterConfig(BookHandler bookHandler) {
        this.bookHandler = bookHandler;
    }

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .path("/route", builder -> builder
                        .GET("/books",bookHandler::getBooks)
                        .GET("/books/{bookId}",bookHandler::getBookById))
                .build();
    }
}
