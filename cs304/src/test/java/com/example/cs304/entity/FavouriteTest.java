package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FavouriteTest {

    private Favourite favouriteUnderTest;

    @BeforeEach
    void setUp() {
        favouriteUnderTest = new Favourite();
        favouriteUnderTest.setUserId(1);
        favouriteUnderTest.setGoodId(1);
    }

    @Test
    void testGetUserId() {
        // Test when userId is set
        favouriteUnderTest.setUserId(1);
        assertThat(favouriteUnderTest.getUserId()).isEqualTo(1);

        // Test when userId is not set
        favouriteUnderTest.setUserId(null);
        assertThat(favouriteUnderTest.getUserId()).isNull();
    }

    @Test
    void testGetGoodId() {
        // Test when goodId is set
        favouriteUnderTest.setGoodId(1);
        assertThat(favouriteUnderTest.getGoodId()).isEqualTo(1);

        // Test when goodId is not set
        favouriteUnderTest.setGoodId(null);
        assertThat(favouriteUnderTest.getGoodId()).isNull();
    }

    @Test
    void testEqualsAndHashCode() {
        Favourite anotherFavourite = new Favourite();
        anotherFavourite.setUserId(1);
        anotherFavourite.setGoodId(1);

        // Test when all attributes are equal
        assertThat(favouriteUnderTest).isEqualTo(anotherFavourite);
        assertThat(favouriteUnderTest.hashCode()).isEqualTo(anotherFavourite.hashCode());

        // Test when userId is different
        anotherFavourite.setUserId(2);
        assertThat(favouriteUnderTest).isNotEqualTo(anotherFavourite);
        assertThat(favouriteUnderTest.hashCode()).isNotEqualTo(anotherFavourite.hashCode());

        // Test when goodId is different
        anotherFavourite.setUserId(1);
        anotherFavourite.setGoodId(2);
        assertThat(favouriteUnderTest).isNotEqualTo(anotherFavourite);
        assertThat(favouriteUnderTest.hashCode()).isNotEqualTo(anotherFavourite.hashCode());
    }
}
