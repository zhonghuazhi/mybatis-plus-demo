package net.cc.demo.controller;

import net.cc.demo.dao.UserMapper;
import net.cc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhonghua.zhi
 * @version 1.0
 * @classname UserController
 * @date 2020-01-08 11:46
 * @description TODO
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 功能描述:
     * 基础查询-（通过主键查询数据表）只可传入一个Id
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/8 11:55 上午
     */
    @GetMapping("/baseQueryId")
    public void baseQueryById() {

        User user = userMapper.selectById("1001");
        if (null != user) {
            System.out.println("user = " + user.toString());
        } else {
            System.out.println("查询为空");
        }
    }

    /**
     * 功能描述:
     * 基础查询-（通过主键查询数据表）可传入Id列表
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/8 11:58 上午
     */
    @GetMapping("/baseQueryIds")
    public void baseQueryByIds() {

        List<User> list = userMapper.selectBatchIds(
                Arrays.asList(1087982257332887553L, 1088248166370832385L));
        if (null != list && !list.isEmpty()) {
            list.forEach(System.out::println);
        } else {
            System.out.println("查询为空");
        }
    }
}