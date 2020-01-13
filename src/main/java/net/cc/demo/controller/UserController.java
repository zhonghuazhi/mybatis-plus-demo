package net.cc.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.cc.demo.dao.UserMapper;
import net.cc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * SQL: where id = ?
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
     * 基础查询-（通过主键查询数据表）可传入【主键Id列表】
     * SQL: where id in (?,?)
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

    /**
     * 功能描述:
     * 基础查询-（通过自定义字段查询数据表）
     * SQL: where key = ? and key = ?
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/8 2:04 下午
     */
    @GetMapping("/baseQueryMap")
    public void baseQueryByMap() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", 25);
        map.put("email", "wtf@baomidou.com");

        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询 (like 使用 , 小于使用)
     * 程序用例：where email like %wt% and age < 40
     * SQL: where key like %?% and key < ?
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/8 2:20 下午
     */
    @GetMapping("/wrapperQuery1")
    public void wrapperQuery1() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("email", "wt").lt("age", 40);

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询 (like 使用 , 区间使用 , NotNull使用)
     * 程序用例：where email like %wt% and age between 20 and 40 and name is not null
     * 注： between ( <= && >= )
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/10 11:02 上午
     */
    @GetMapping("/wrapperQuery2")
    public void wrapperQuery2() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("email", "wt")
                .between("age", 20, 40).isNotNull("name");

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询 (like 使用 , or使用 , desc、asc使用)
     * 程序用例：where email like wt% or age >= 25 order by age desc , id asc
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/10 11:27 上午
     */
    @GetMapping("/wrapperQuery3")
    public void wrapperQuery3() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("email", "wt")
                .or().ge("age", 25).orderByDesc("age").orderByAsc("id");

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询（调用mysql函数、子查询）
     * 程序用例：where date_format(create_time , '%Y-%m-%d') and manager_id in (子查询)
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/10 11:35 上午
     */
    @GetMapping("/wrapperQuery4")
    public void wrapperQuery4() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        /** 在使用时 建议使用占位符方式 ，防止SQL注入 **/
        queryWrapper.apply("date_format(create_time ,'%Y-%m-%d') = {0}", "2019-02-05")
                .inSql("manager_id", "select id from user where email like 'boss%'");

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询
     * 程序用例: where email like 'wt%' and (age < 40 or id > 1)
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/10 4:21 下午
     */
    @GetMapping("/wrapperQuery5")
    public void wrapperQuery5() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("email", "wt")
                .and(qw -> qw.lt("age", 40).or().gt("id", 1));

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询
     * 程序用例：where email like 'wt%' or (age <= 40 and age >= 20  and create_time is not null)
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/10 4:28 下午
     */
    @GetMapping("/wrapperQuery6")
    public void wrapperQuery6() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("email", "wt")
                .or(qw -> qw.between("age", 20, 40).isNotNull("create_time"));

        /**
         * between : (20 ,40) 表示
         */
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询
     * 程序用例：where (age < 40 or email is not null) and name like '王%'
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/11 11:12 下午
     */
    @GetMapping("/wrapperQuery7")
    public void wrapperQuery7() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.nested(qw -> qw.lt("age", 40)
                .or().isNotNull("email")).likeRight("email", "wt");

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询
     * 程序用例：where age in (30、31、34、35)
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/11 11:22 下午
     */
    @GetMapping("/wrapperQuery8")
    public void wrapperQuery8() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.in("age", 30, 31, 34, 35);

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询 (limit)
     * 程序用例：where age in (30、31、34、35) limit 1
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/11 11:28 下午
     */
    @GetMapping("/wrapperQuery9")
    public void wrapperQuery9() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询  + 指定返回 Select 列
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/11 11:35 下午
     */
    @GetMapping("/wrapperQuerySupper1")
    public void wrapperQuerySupper1() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name", "age").lt("age", 40);

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询  + 指定返回非显示列 (与 wrapperQuerySupper1函数 相反)
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/11 11:45 下午
     */
    @GetMapping("wrapperQuerySupper2")
    public void wrapperQuerySupper2() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select(User.class, tf -> !tf.getColumn().equals("age") && !tf.getColumn().equals("email"));

        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 构造器条件查询 （Lambda）
     *
     * @param
     * @return: void
     * @exception: v
     * @author: zhonghua.zhi
     * @date: 2020/1/12 5:07 下午
     */
    @GetMapping("/wrapperQuerylambda")
    public void wrapperQuerylambda() {

        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeRight(User::getEmail, "wt")
                .and(lqw -> lqw.lt(User::getAge, 40).isNotNull(User::getEmail));

        List<User> list = userMapper.selectList(lambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 求总记录数
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/12 5:10 下午
     */
    @GetMapping("/wrapperQueryCount")
    public void wrapperQueryCount() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        Integer counts = userMapper.selectCount(queryWrapper);
        System.out.println("counts == " + counts);
    }

    /**
     * 功能描述:
     * 返回 Map
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/12 5:14 下午
     */
    @GetMapping("/wrapperQueryMap")
    public void wrapperQueryMap() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name");

        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 功能描述:
     * 自定义SQL
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/12 5:44 下午
     */
    @GetMapping("/custom1")
    public void custom1() {

        User user = userMapper.selectCustom();
        System.out.println(user);
    }

    /**
     * 功能描述:
     * 分页查询
     *
     * @param
     * @return: void
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/12 8:35 下午
     */
    @GetMapping("/selectPage")
    public void selectPage() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age", 1);

        /** 当前第一页 每页显示两条 **/
        Page<User> page = new Page<User>(1, 2);

        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数： " + iPage.getPages());
        System.out.println("总记录数：" + iPage.getTotal());

        List<User> users = iPage.getRecords();
        users.forEach(System.out::println);
    }
}