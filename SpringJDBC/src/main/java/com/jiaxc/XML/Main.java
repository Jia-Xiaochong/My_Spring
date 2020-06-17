package com.jiaxc.XML;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    @Test
    public void test() {
        String xmlPath = "XML_beans.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.transfer("zhangsan", "lisi", 100);
    }
}
