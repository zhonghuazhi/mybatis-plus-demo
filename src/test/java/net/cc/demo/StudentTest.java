package net.cc.demo;

import net.cc.demo.dao.StudentMapper;
import net.cc.demo.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhonghua.zhi
 * @version 1.0
 * @classname StudentTest
 * @date 2020-01-12 18:43
 * @description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testSelect() {

        System.out.println("------------------ Test");

        List<Student> list = studentMapper.selectList(null);
        Assert.assertEquals(5, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {

        Student student = new Student();
        student.setName("ZhangSan");
        student.setAge(29);
        student.setEmail("zhangsan@126.com");

        int op = studentMapper.insert(student);
        Assert.assertEquals(1, op);
    }
}