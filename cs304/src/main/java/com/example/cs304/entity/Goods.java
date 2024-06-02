package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Schema
@Data
public class Goods {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private BigDecimal price;
    private String image;
    private String sellerId;
    private String buyerId;
    private String description;
    private String category;
    private Date publishDate;
}
