package com.jiaxc.container;

import com.jiaxc.dao.Book;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class MyBeanFactory {
    @Test
    public void one() {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        Book obj = (Book) factory.getBean("newBook");
        System.out.println(obj.getName());
    }
    @Test
    public void two() {
        BeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
        beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        Book obj = (Book) factory.getBean("newBook");
        System.out.println(obj.getName());
    }
}
