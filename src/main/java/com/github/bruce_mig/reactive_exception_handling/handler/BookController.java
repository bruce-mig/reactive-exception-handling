package com.github.bruce_mig.reactive_exception_handling.handler;

import com.github.bruce_mig.reactive_exception_handling.dao.BookRepository;
import com.github.bruce_mig.reactive_exception_handling.dto.Book;
import com.github.bruce_mig.reactive_exception_handling.exception.BookAPIException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Book> getBooks() {
        return repository.getBooks();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable int id) {
        return repository.getBooks()
                .filter(book -> book.getBookId() == id)
                .next()
                .switchIfEmpty(Mono.error(new BookAPIException("Book not found with id " + id)));
    }
}
