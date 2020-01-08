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

    /**
     * 功能描述:
     * 正常查询（通过一个主键查询）
     *
     * @param
     * @return: void
     * @exception:
     * @author:
     * @date: 2020/1/7 7:06 下午
     */
    @Test
    public void query1() {
        CcUser ccUser = ccUserMapper.selectById("1214438712841486338");
        System.out.println(ccUser);
    }


    /**
     * 功能描述:
     * 正常查询（通过一批主键查询）
     *
     * @param
     * @return: void
     * @exception:
     * @author:
     * @date: 2020/1/7 7:07 下午
     */
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

    /**
     * 功能描述:
     * 正常查询（通过指定字段进行查询） 只能相等查询
     *
     * @param
     * @return: void
     * @exception:
     * @author:
     * @date: 2020/1/7 7:08 下午
     */
    @Test
    public void query3() {

        Map<String, Object> mapParams = new HashMap<String, Object>();
        mapParams.put("name", "Test");

        List<CcUser> res = ccUserMapper.selectByMap(mapParams);
        res.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 正常查询（通过指定字段进行查询） 可进行like ｜ 大于 ｜ 小于 等查询
     *
     * @param
     * @return: void
     * @exception:
     * @author:
     * @date: 2020/1/7 7:09 下午
     */
    @Test
    public void query4() {

        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<CcUser>();
        queryWrapper.like("name", "AAAA").lt("aget", 40);

        List<CcUser> res = ccUserMapper.selectList(queryWrapper);
        res.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 正常查询（通过指定字段进行查询） apply 查询
     *
     * @param
     * @return: void
     * @exception:
     * @author:
     * @date: 2020/1/7 7:56 下午
     */
    public void query5() {

        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<CcUser>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-01-01")
                .eq("email", "boss@163.com");

        List<CcUser> res = ccUserMapper.selectList(queryWrapper);
        res.forEach(System.out::println);
    }

    /**
     * 功能描述:
     *
     * @param
     * @return: void
     * @exception:
     * @author:
     * @date: 2020/1/7 11:28 下午
     */
    public void query6() {

        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<CcUser>();
        queryWrapper.likeRight("name", "王")
                .and(ab -> ab.lt("age", 40).or().isNotNull("email"));

        ccUserMapper.selectList(queryWrapper);
    }

    /**
     * 功能描述:
     * 正常查询（显示特定的字段信息） 特定查询查询
     *
     * @param
     * @return: void
     * @exception:
     * @author:
     * @date: 2020/1/8 8:04 上午
     */
    @Test
    public void query7() {

        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<CcUser>();
        queryWrapper.select("id", "name").gt("aget", 30);

        List<CcUser> list = ccUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}