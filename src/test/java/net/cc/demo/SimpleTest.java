package net.cc.demo;

import net.cc.demo.dao.CcUserMapper;
import net.cc.demo.entity.CcUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @classname SimpleTest
 * @date 2020-01-07 00:20
 * @description TODO
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleTest {

    @Autowired
    private CcUserMapper ccUserMapper;

    @Test
    public void select() {
        List<CcUser> list = ccUserMapper.selectList(null);
        Assert.assertEquals(3, list.size());

        list.forEach(System.out::println);
    }
}