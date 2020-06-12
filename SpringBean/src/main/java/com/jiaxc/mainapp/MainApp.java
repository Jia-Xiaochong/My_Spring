package com.jiaxc.mainapp;

import com.jiaxc.dao.Book;
import com.jiaxc.dao.Class;
import com.jiaxc.dao.Library;
import com.jiaxc.dao.Student;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    @Test
    public void test() {
        // 配置回调方法
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Book obj = (Book) context.getBean("newBook");
        System.out.println(obj.getName());
        // 在这里，你需要注册一个在 AbstractApplicationContext 类中声明的关闭 hook 的 registerShutdownHook() 方法。
        // 它将确保正常关闭，并且调用相关的 destroy 方法。
        context.registerShutdownHook();
    }
    @Test
    public void testA() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Library obj = (Library) context.getBean("library");
        System.out.println(obj.getLibraryName());
        context.registerShutdownHook();
    }
    @Test
    public void testB() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
        Student obj = (Student) context.getBean("student");
        System.out.println(obj.getMyClass().getClassName());
        context.registerShutdownHook();
    }
}
