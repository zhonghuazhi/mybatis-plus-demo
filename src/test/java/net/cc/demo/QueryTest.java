package net.cc.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.cc.demo.dao.CcUserMapper;
import net.cc.demo.entity.CcUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @classname QueryTest
 * @date 2020-01-07 16:54
 * @description TODO
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QueryTest {

    @Autowired
    private CcUserMapper ccUserMapper;

    @Test
    public void query1() {
        CcUser ccUser = ccUserMapper.selectById("1214438712841486338");
        System.out.println(ccUser);
    }

    @Test
    public void query2() {

        List<String> list = new ArrayList<String>();
        list.add("1001");
        list.add("1002");
        list.add("1003");

        List<CcUser> res = ccUserMapper.selectBatchIds(list);
        for (CcUser user : res) {
            System.out.println(user.getId() + " " + user.getName());
        }
    }

    @Test
    public void query3() {

        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("name", "Test");

        List<CcUser> res = ccUserMapper.selectByMap(mapParams);
        res.forEach(System.out::println);
    }

    @Test
    public void query4(){

        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<CcUser>();
        queryWrapper.like("name" ,"AAAA").lt("aget" ,40);

        List<CcUser> res = ccUserMapper.selectList(queryWrapper);
        res.forEach(System.out::println);
    }
}