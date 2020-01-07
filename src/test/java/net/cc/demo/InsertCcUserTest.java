package net.cc.demo;

import net.cc.demo.dao.CcUserMapper;
import net.cc.demo.entity.CcUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author
 * @version 1.0
 * @classname InsertCcUserTest
 * @date 2020-01-07 14:27
 * @description TODO
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertCcUserTest {

    @Autowired
    private CcUserMapper ccUserMapper;

    @Test
    public void insertTest() {

        CcUser ccUser = new CcUser();
        ccUser.setName("AAAA");
        ccUser.setAget(30);
        ccUser.setEmail("fjdklfXX@126.com");
        ccUser.setManagerId(new Long(1001));
        ccUser.setCreateTime(LocalDateTime.now());
        ccUser.setRemark("Test Mark ~~~");

        int op = ccUserMapper.insert(ccUser);

        Assert.assertEquals(1, op);
    }
}