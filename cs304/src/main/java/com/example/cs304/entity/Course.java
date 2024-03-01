package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String traingType;

    private String courseCode;

    private String chineseName;

    private String englishName;

    private String courseNature;//课程性质

    private String courseCategory;

    private String language;

    private Double credits;

    private Double hours;

    private String department; //开课院系

    private String teacher; //老师名字
}
