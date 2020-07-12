package com.accenture.acme.store.shoppingcart;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {
    private final long productId = 2020l;
    private final String description = "Product 2020";
    private final int quantity = 2;
    private final double weight = 0.500d;
    private final BigDecimal price = BigDecimal.TEN;

    @Test
    public void shouldBeEqualWhenGivenSameObject(){
        // Given
        Item item1 = new Item(productId, description, quantity, weight, price);

        // When
        boolean result = item1.equals(item1);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldBeEqualWhenGivenTheSameProductId(){
        // Given
        Item item1 = new Item(productId, description, quantity, weight, price);
        Item item2 = new Item(productId, description, quantity, weight, price);

        // When
        boolean result = item1.equals(item2);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldNotEqualWhenGivenADifferentClass(){
        // Given
        Item item1 = new Item(productId, description, quantity, weight, price);

        // When
        boolean result = item1.equals(this);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotEqualWhenGivenADifferentProductId(){
        // Given
        Item item1 = new Item(productId, description, quantity, weight, price);
        Item item2 = new Item(Long.MIN_VALUE, description, quantity, weight, price);

        // When
        boolean result = item1.equals(item2);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldGenerateTheSameHashCodeWhenGivenTheSameProductId(){
        // Given
        Item item1 = new Item(productId, description, quantity, weight, price);
        Item item2 = new Item(productId, description, quantity, weight, price);

        // When
        boolean result = item1.hashCode() == item2.hashCode();

        // Then
        assertThat(result).isTrue();
    }

}
