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

    private String teachingClass;

    private String traingType;

    private String courseCode;

    private String courseName;


}
