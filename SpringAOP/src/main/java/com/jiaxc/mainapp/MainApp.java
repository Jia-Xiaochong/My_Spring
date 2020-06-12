package com.jiaxc.mainapp;

import com.jiaxc.dao.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    @Test
    public void testBean1() {
        String xmlPath = "beans1.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        Book b = (Book) context.getBean("bookProxy");
        b.getName();
    }
    @Test
    public void testBean2() {
        String xmlPath = "beans2.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        Book b = (Book) context.getBean("book");
        b.getName();
    }
    @Test
    public void testBeans3() {
        String xmlPath = "beans3.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        Book b = (Book) context.getBean("book");
        b.getName();
    }
}
