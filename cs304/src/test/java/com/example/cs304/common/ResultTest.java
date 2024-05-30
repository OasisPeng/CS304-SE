package com.example.cs304.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    private Result resultUnderTest;
    private Result anotherResult;

    @BeforeEach
    void setUp() {
        resultUnderTest = new Result(200, "成功", "data");
        anotherResult = new Result(200, "成功", "data");
    }

    @Test
    void testResultAttributes() {
        assertThat(resultUnderTest.getCode()).isEqualTo(200);
        assertThat(resultUnderTest.getMsg()).isEqualTo("成功");
        assertThat(resultUnderTest.getData()).isEqualTo("data");
    }

    @Test
    void testEqualsAndHashCode() {
        // Test when all attributes are equal
        assertThat(resultUnderTest).isEqualTo(anotherResult);
        assertThat(resultUnderTest.hashCode()).isEqualTo(anotherResult.hashCode());

        // Test when code is different
        anotherResult.setCode(400);
        assertThat(resultUnderTest).isNotEqualTo(anotherResult);
        assertThat(resultUnderTest.hashCode()).isNotEqualTo(anotherResult.hashCode());

        // Reset code and test when msg is different
        anotherResult.setCode(200);
        anotherResult.setMsg("失败");
        assertThat(resultUnderTest).isNotEqualTo(anotherResult);
        assertThat(resultUnderTest.hashCode()).isNotEqualTo(anotherResult.hashCode());

        // Reset msg and test when data is different
        anotherResult.setMsg("成功");
        anotherResult.setData("different_data");
        assertThat(resultUnderTest).isNotEqualTo(anotherResult);
        assertThat(resultUnderTest.hashCode()).isNotEqualTo(anotherResult.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Result(code=200, msg=成功, data=data)";
        assertThat(resultUnderTest.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testFailMethod() {
        Result failResult = Result.fail();
        assertThat(failResult.getCode()).isEqualTo(400);
        assertThat(failResult.getMsg()).isEqualTo("失败");
        assertThat(failResult.getData()).isNull();

        failResult = Result.fail("Custom failure message");
        assertThat(failResult.getCode()).isEqualTo(400);
        assertThat(failResult.getMsg()).isEqualTo("Custom failure message");
        assertThat(failResult.getData()).isNull();
    }

    @Test
    void testSucMethod() {
        Result successResult = Result.suc("success_data");
        assertThat(successResult.getCode()).isEqualTo(200);
        assertThat(successResult.getMsg()).isEqualTo("成功");
        assertThat(successResult.getData()).isEqualTo("success_data");
    }

    @Test
    void testSetters() {
        resultUnderTest.setCode(400);
        resultUnderTest.setMsg("失败");
        resultUnderTest.setData("new_data");

        assertThat(resultUnderTest.getCode()).isEqualTo(400);
        assertThat(resultUnderTest.getMsg()).isEqualTo("失败");
        assertThat(resultUnderTest.getData()).isEqualTo("new_data");
    }
}
