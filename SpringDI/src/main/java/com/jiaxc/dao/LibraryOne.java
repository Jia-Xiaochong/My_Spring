package com.jiaxc.dao;

public class LibraryOne {

    private Book book;

    public LibraryOne(Book book) {
        this.book = book;
    }

    public void addBook() {
        System.out.println(book.getName() + "已加入图书馆①");
    }

}
