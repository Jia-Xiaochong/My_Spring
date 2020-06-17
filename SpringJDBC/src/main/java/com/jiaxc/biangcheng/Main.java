package com.jiaxc.biangcheng;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("biancheng.xml");
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
        System.out.println("------创建记录--------" );
        studentJDBCTemplate.create("Zara", 11, 99, 2010);
        studentJDBCTemplate.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate.create("Ayan", 25, 100, 2011);
        System.out.println("------记录列表--------" );
        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
        for (StudentMarks record : studentMarks) {
            System.out.print("ID : " + record.getId() + "\t");
            System.out.print("| Name : " + record.getName() + "\t");
            System.out.print("| Marks : " + record.getMarks() + "\t");
            System.out.print("| Year : " + record.getYear() + "\t");
            System.out.println("| Age : " + record.getAge());
        }
    }
}
