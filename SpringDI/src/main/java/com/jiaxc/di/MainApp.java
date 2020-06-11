package com.jiaxc.di;

import com.jiaxc.dao.JavaCollection;
import com.jiaxc.dao.LibraryOne;
import com.jiaxc.dao.LibraryTwo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    @Test
    public void one() {
        // 基于构造函数的依赖注入
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        LibraryOne obj = (LibraryOne) applicationContext.getBean("libraryOne");
        obj.addBook();
    }
    @Test
    public void two() {
        // 基于设值函数的依赖注入
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        LibraryTwo obj = (LibraryTwo) applicationContext.getBean("libraryTwo");
        obj.addBook();
    }
    @Test
    public void three() {
        // 注入内部 Beans
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        LibraryTwo obj = (LibraryTwo) applicationContext.getBean("libraryThree");
        obj.addBook();
    }
    @Test
    public void collection() {
        // 注入集合
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        JavaCollection jc = (JavaCollection) context.getBean("javaCollection");
        jc.getAddressList();
        jc.getAddressSet();
        jc.getAddressMap();
        jc.getAddressProp();
    }
}
