package com.jiaxc.dao;

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

    public void myInit() {
        System.out.println("我打开了这本书。");
    }

    public void myDestroy() {
        System.out.println("我合上了这本书。");
    }

}
