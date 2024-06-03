package com.example.cs304.controller;


import com.example.cs304.common.AliOssUtil;
import com.example.cs304.common.Result;
import com.example.cs304.entity.Goods;
import com.example.cs304.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.output.support.SAXOutputProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
@Tag(name = "Goods")
@Slf4j
public class GoodsController {
    @Autowired
    AliOssUtil util;

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "上传商品图片")
    public Result upload(@RequestPart("file") MultipartFile file) {
        log.info("文件上传：{}",file);
        try {
            //文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID() +extension;
            //返回文件的路径
            String filePath = util.upload(file.getBytes(), objectName);
            return Result.suc(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return Result.fail();
    }

    @GetMapping("/category/{category}")
    @Operation(description = "查询指定类别的所有商品")
    public Result getGoodsByCategory(@PathVariable String category) {
//        System.out.println("hahahahah");
        return Result.suc(goodsService.findByCategory(category));
    }

    @GetMapping("/seller/{sellerId}")
    @Operation(description = "查询指定卖家学号的所有商品")
    public Result getGoodsBySellerId(@PathVariable String sellerId) {
        return Result.suc(goodsService.findBySellerId(sellerId));
    }

    @GetMapping("/buyer/{buyerId}")
    @Operation(description = "查询指定买家学号的所有商品")
    public Result getGoodsByBuyerId(@PathVariable String buyerId) {
        return Result.suc(goodsService.findByBuyerId(buyerId));
    }

    @GetMapping("/{id}")
    @Operation(description = "查询单个商品")
    public Result getGoodsById(@PathVariable Long id) {
        return Result.suc(goodsService.findById(id));
    }

    @GetMapping("/search")
    @Operation(description = "搜索带有名称、类别、描述中带有关键词的商品")
    public Result searchGoods(@RequestParam String keyword) {
        return Result.suc(goodsService.searchByKeyword(keyword));
    }

    @PostMapping("/add")
    @Operation(description = "新增商品")
    public Result addGoods(@RequestBody Goods goods) {
        log.info("Received goods: {}", goods);
        System.out.println(goods.getImage());
        return (goodsService.insert(goods) != null) ? Result.suc(goods):Result.fail();
    }

    @PostMapping("/update")
    @Operation(description = "修改商品属性，如买家id")
    public Result updateGoods(@RequestBody Goods goods) {
        System.out.println("Received goods: "+ goods);
        return (goodsService.updateGoods(goods) != null) ? Result.suc(goods):Result.fail();
    }

    @PostMapping("/del")
    @Operation(description = "删除商品")
    public Result deleteGoods(@RequestBody Goods goods) {
        System.out.println("删除商品："+goods);
        return (goodsService.deleteById(goods) != null) ? Result.suc(goods):Result.fail();
    }
}
