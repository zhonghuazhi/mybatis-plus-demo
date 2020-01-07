package net.cc.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 注解说明：
 * 1 @TableName 表示此类对应的数据库表名称
 * 2 @TableId 表示此属性为数据库表主键
 * 3 @TableField 表示此属性对应数据库表哪个字段
 * 4 @TableField(exist = false) 表示此属性不对应数据库表任何字段（框架忽略转换SQL）
 *
 * @author
 * @version 1.0
 * @classname CcUser
 * @date 2020-01-07 00:15
 * @description TODO
 */
@Data
@TableName("cc_user")
public class CcUser {

    /**
     * 主键
     **/
    @TableId
    private Long id;

    /**
     * 姓名
     **/
    @TableField("name")
    private String name;

    /**
     * 年龄
     **/
    private Integer aget;

    /**
     * 邮箱
     **/
    private String email;

    /**
     * 直属上级
     **/
    private Long managerId;

    /**
     * 创建时间
     **/
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @TableField(exist = false)
    private String remark;
}
