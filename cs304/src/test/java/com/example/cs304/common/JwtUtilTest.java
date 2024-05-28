package com.example.cs304.common;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private JwtUtil jwtUtilUnderTest;

    @Mock
    private Cache<String, String> jwtCache = Caffeine.newBuilder().maximumSize(1000).expireAfterWrite(300, TimeUnit.MINUTES).build();

    @BeforeEach
    void setUp() {
        jwtUtilUnderTest = new JwtUtil();
        jwtUtilUnderTest.secretKey = "KgszcQOvIT9j04Ri6Mn7Eo2H1CX8cXxquswNHDiyc34=";
        jwtUtilUnderTest.jwtCache = jwtCache;
    }

    @Test
    void testInit() {
        // Setup
        // Run the test
        jwtUtilUnderTest.init();
    }

    @Test
    void testCreateJWT() {
        // Setup
        final Map<String, Object> claims = new HashMap<>();
        claims.put("value", "123");

        // Run the test
        final String result = jwtUtilUnderTest.createJWT(0, claims);

        // Verify the results
        assertThat(result).isInstanceOf(String.class);
    }


    @Test
    void testParseJWT() {
        // Setup
        jwtCache.put("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTY5Mjk4NDgsInVzZXJuYW1lIjoiMTIxMTE1NDgifQ.MVOOs-lPmuI48FKNyekQxJUIB0f14FGoR3a-0Nne_pY","");
        // Run the test
        final String result1 = jwtUtilUnderTest.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTY5Mjk4NDgsInVzZXJuYW1lIjoiMTIxMTE1NDgifQ.MVOOs-lPmuI48FKNyekQxJUIB0f14FGoR3a-0Nne_pY");
        final String result2 = jwtUtilUnderTest.parseJWT("eee");

        // Verify the results
        assertThat(result1).isEqualTo("12111548");
        assertThat(result2).isEqualTo(null);
    }

    @Test
    void testExtractJWT() {
        // Setup
        final MockHttpServletRequest request = new MockHttpServletRequest();

        // Run the test
        final String result = jwtUtilUnderTest.extractJWT(request);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testInvalidJWT() {
        // Setup
        // Run the test
        jwtUtilUnderTest.invalidJWT("token");

        // Verify the results
    }
}
