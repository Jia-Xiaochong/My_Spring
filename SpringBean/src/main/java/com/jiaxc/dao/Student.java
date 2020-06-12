package com.jiaxc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Student {

    @Autowired
    @Qualifier("class2")
    private Class myClass;
    private String studentName;

    public Student() {}

    // @Autowired
    public Student(Class myClass) {
        this.myClass = myClass;
    }

    public Class getMyClass() {
        return myClass;
    }

    // @Autowired
    public void setMyClass(Class myClass) {
        this.myClass = myClass;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
