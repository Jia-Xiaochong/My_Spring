package com.jiaxc.container;

import com.jiaxc.dao.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MyApplicationContext {
    @Test
    public void applicationContextOne() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Book obj = (Book) applicationContext.getBean("newBook");
        System.out.println(obj.getName());
    }
    @Test
    public void applicationContextTwo() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/resources/beans.xml");
        Book obj = (Book) applicationContext.getBean("newBook");
        System.out.println(obj.getName());
    }
}
