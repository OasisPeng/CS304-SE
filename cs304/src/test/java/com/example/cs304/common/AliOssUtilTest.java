package com.example.cs304.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AliOssUtilTest {

    private AliOssUtil aliOssUtilUnderTest;

    @BeforeEach
    void setUp() {
        aliOssUtilUnderTest = new AliOssUtil();
        aliOssUtilUnderTest.endpoint = "oss-cn-beijing.aliyuncs.com";
        aliOssUtilUnderTest.accessKeyId = "LTAI5tHd81k9sjZ4AtV5RtZq";
        aliOssUtilUnderTest.accessKeySecret = "zysTqJGfyjfCTtKHaSAyWL5Da9K8qz";
        aliOssUtilUnderTest.bucketName = "sky-aliyunitcast";
    }

    @Test
    void testUpload() {
        assertThat(aliOssUtilUnderTest.upload("content".getBytes(), "objectName")).isEqualTo("https://sky-aliyunitcast.oss-cn-beijing.aliyuncs.com/objectName");
    }
}
