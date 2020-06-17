package com.jiaxc.dao;

import javax.sql.DataSource;
import java.util.List;

public interface StudentDAO {
    /**
     * 数据库连接
     */
    public void setDataSource(DataSource ds);
    /**
     * 创建学生
     */
    public void create(String name, Integer age);
    /**
     * 获取学生信息，根据学生id
     */
    public Student getStudent(Integer id);
    /**
     * 获取学生列表
     */
    public List<Student> listStudents();
    /**
     * 删除学生，根据学生id
     */
    public void delete(Integer id);
    /**
     * 修改学生年龄，根据学生id
     */
    public void update(Integer id, Integer age);
}
