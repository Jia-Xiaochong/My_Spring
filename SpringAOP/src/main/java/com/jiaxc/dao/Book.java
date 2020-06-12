package com.jiaxc.dao;

import org.springframework.stereotype.Repository;

@Repository("book")
public class Book {

    private String name;

    public Book() {
        super();
    }

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
