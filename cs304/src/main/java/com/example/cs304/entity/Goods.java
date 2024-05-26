package com.example.cs304.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Schema
@Data
public class Goods {
    private Long id;
    private String name;
    private BigDecimal price;
    private String image;
    private String sellerId;
    private String buyerId;
    private String description;
    private String category;
    private Date publishDate;
}
