package com.github.bruce_mig.reactive_exception_handling.dto;

import java.util.Objects;

public class Book {
    private int bookId;
    private String name;
    private double price;

    public Book(int bookId, String name, double price) {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && Double.compare(price, book.price) == 0 && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, name, price);
    }
}
