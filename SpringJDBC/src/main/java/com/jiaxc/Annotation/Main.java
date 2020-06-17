package com.jiaxc.Annotation;

import com.jiaxc.XML.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    @Test
    public void test() {
        String xmlPath = "Annotation_beans.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.transfer("lisi", "zhangsan", 100);
    }
}
