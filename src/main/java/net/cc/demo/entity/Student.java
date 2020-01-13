package net.cc.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhonghua.zhi
 * @version 1.0
 * @classname Student
 * @date 2020-01-12 18:35
 * @description TODO
 */
@Data
@TableName("student")
public class Student {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Integer age;

    private String email;
}