/**
 * AI-generated-content * tool: ChatGPT
 * version: 3.5
 * usage: I used the prompt "怎么给Goods类写单元测试", and use the code from its response after some modification
 */
package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

class GoodsTest {

    private Goods goods;

    @BeforeEach
    public void setUp() {
        goods = new Goods();
        goods.setId(1L);
        goods.setName("Test Product");
        goods.setPrice(new BigDecimal("99.99"));
        goods.setImage("image.jpg");
        goods.setSellerId("seller123");
        goods.setBuyerId("buyer456");
        goods.setDescription("A test product description.");
        goods.setCategory("Test Category");
        goods.setPublishDate(new Date());
    }

    @Test
    public void testGoodsAttributes() {
        assertThat(goods.getId()).isEqualTo(1L);
        assertThat(goods.getName()).isEqualTo("Test Product");
        assertThat(goods.getPrice()).isEqualByComparingTo("99.99");
        assertThat(goods.getImage()).isEqualTo("image.jpg");
        assertThat(goods.getSellerId()).isEqualTo("seller123");
        assertThat(goods.getBuyerId()).isEqualTo("buyer456");
        assertThat(goods.getDescription()).isEqualTo("A test product description.");
        assertThat(goods.getCategory()).isEqualTo("Test Category");
        assertThat(goods.getPublishDate()).isNotNull();
    }

    @Test
    public void testGoodsToString() {
        String toString = goods.toString();
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("name=Test Product");
        assertThat(toString).contains("price=99.99");
        assertThat(toString).contains("image=image.jpg");
        assertThat(toString).contains("sellerId=seller123");
        assertThat(toString).contains("buyerId=buyer456");
        assertThat(toString).contains("description=A test product description.");
        assertThat(toString).contains("category=Test Category");
    }
}
