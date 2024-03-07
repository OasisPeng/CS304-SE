package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cs304
 * @since 2024-02-27
 */
@Schema
//@ApiModel(value = "Course对象", description = "")
    @Data
public class Course implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("trainingType")
    private String trainingType;

    @TableField("courseCode")
    private String courseCode;

    @TableField("chineseName")
    private String chineseName;

    @TableField("englishName")
    private String englishName;

    @TableField("courseNature")
    private String courseNature;//课程性质

    @TableField("courseCategory")
    private String courseCategory;

    private String language;

    private Double credits;

    private Double hours;

    private String department; //开课院系

    private String teacher; //老师名字
}
