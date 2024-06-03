package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 待办事件
 * </p>
 *
 * @author cs304
 * @since 2024-03-06
 */
@Schema
@Data
public class Event implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建者username
     */
    private String owner;

    /**
     * 第几周
     */
    private Integer week;

    /**
     * 星期
     */
    private Integer xq;

    /**
     * 待办事件紧急程度
     */
    private String level;

    /**
     * 待办是否完成
     */
    private Integer finish;

    /**
     * 待办类别
     */
    private String category;

    /**
     * 心情
     */
    private String emotion;

    /**
     * 内容
     */
    private String text;






}
