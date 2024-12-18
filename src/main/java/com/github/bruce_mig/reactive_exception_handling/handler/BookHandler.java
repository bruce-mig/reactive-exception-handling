package com.github.bruce_mig.reactive_exception_handling.handler;

import com.github.bruce_mig.reactive_exception_handling.dao.BookRepository;
import com.github.bruce_mig.reactive_exception_handling.dto.Book;
import com.github.bruce_mig.reactive_exception_handling.exception.BookAPIException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookHandler {

    private final BookRepository bookRepository;

    public BookHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<ServerResponse> getBooks(ServerRequest request) {
        Flux<Book> books = bookRepository.getBooks();;
        return ServerResponse.ok().body(books, Book.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("bookId"));
        Mono<Book> bookMono = bookRepository.getBooks()
                .filter(book -> book.getBookId() == id)
                .next()
                .switchIfEmpty(Mono.error(new BookAPIException("Book not found with bookId : " + id)));
        return ServerResponse.ok().body(bookMono, Book.class);
    }
}
