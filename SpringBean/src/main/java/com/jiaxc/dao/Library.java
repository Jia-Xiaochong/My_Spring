package com.jiaxc.dao;

public class Library {
    private String libraryName;

    public Library() {}

    public Library(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void addBook(Book book) {
        System.out.println(book.getName() + "已加入" + this.libraryName);
    }
}
