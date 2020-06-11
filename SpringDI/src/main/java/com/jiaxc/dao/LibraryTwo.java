package com.jiaxc.dao;

public class LibraryTwo {

    private Book book;

    public LibraryTwo() {
        super();
    }

    public LibraryTwo(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addBook() {
        System.out.println(book.getName() + "已加入图书馆②");
    }

}
