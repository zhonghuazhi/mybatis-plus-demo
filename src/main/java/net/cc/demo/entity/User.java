package net.cc.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 相关注解说明：
 * 1. @TableName()  表示此类对应数据库表名称
 * 2. @TableId()  表示此属性为数据库表主键
 * 3. @TableField()  表示此属性对应到数据库表哪个字段
 * 4. @TableField(exist = false) 表示此属性无需映射数据库表
 *
 * @author zhonghua.zhi
 * @version 1.0
 * @classname User
 * @date 2020-01-08 11:42
 * @description TODO
 */
@Data
@TableName("user")
public class User {

    /**
     * 表主键
     */
    @TableId
    private Long id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 直属上级Id
     */
    private Long managerId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}