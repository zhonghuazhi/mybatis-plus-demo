package net.cc.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cc.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhonghua.zhi
 * @version 1.0
 * @classname UserMapper
 * @date 2020-01-08 11:46
 * @description TODO
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 功能描述:
     *
     * @param
     * @return: net.cc.demo.entity.User
     * @exception:
     * @author: zhonghua.zhi
     * @date: 2020/1/12 5:44 下午
     */
    User selectCustom();
}