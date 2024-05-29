package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FollowTest {

    private Follow followUnderTest;

    @BeforeEach
    void setUp() {
        followUnderTest = new Follow();
        followUnderTest.setFollowing(1);
        followUnderTest.setFollower(2);
    }

    @Test
    void testGetFollowing() {
        // Test when following is set
        followUnderTest.setFollowing(1);
        assertThat(followUnderTest.getFollowing()).isEqualTo(1);

        // Test when following is not set
        followUnderTest.setFollowing(null);
        assertThat(followUnderTest.getFollowing()).isNull();
    }

    @Test
    void testGetFollower() {
        // Test when follower is set
        followUnderTest.setFollower(2);
        assertThat(followUnderTest.getFollower()).isEqualTo(2);

    }

    @Test
    void testEqualsAndHashCode() {
        Follow anotherFollow = new Follow();
        anotherFollow.setFollowing(1);
        anotherFollow.setFollower(2);

        // Test when all attributes are equal
        assertThat(followUnderTest).isEqualTo(anotherFollow);
        assertThat(followUnderTest.hashCode()).isEqualTo(anotherFollow.hashCode());

        // Test when following is different
        anotherFollow.setFollowing(3);
        assertThat(followUnderTest).isNotEqualTo(anotherFollow);
        assertThat(followUnderTest.hashCode()).isNotEqualTo(anotherFollow.hashCode());

        // Test when follower is different
        anotherFollow.setFollowing(1);
        anotherFollow.setFollower(4);
        assertThat(followUnderTest).isNotEqualTo(anotherFollow);
        assertThat(followUnderTest.hashCode()).isNotEqualTo(anotherFollow.hashCode());
    }
}
