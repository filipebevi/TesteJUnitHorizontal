package com.accenture.acme.store.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingCartTest {
    private final long productId = 2021l;
    private final String description = "Product 2021";
    private final int quantity = 1;
    private final double weight = 0.100d;
    private final BigDecimal price = BigDecimal.ONE;
    private ShoppingCart shoppingCart;

    @BeforeEach
    public void beforeEach() {
        this.shoppingCart = new ShoppingCart();
    }

    @Test
    public void shouldAddItemCorrectly() {
        // Given
        Item item1 = new Item(productId, description, quantity, weight, price);

        // When
        this.shoppingCart.add(item1);

        // Then
        assertThat(this.shoppingCart.getAll()).isNotNull().isNotEmpty();
        assertThat(this.shoppingCart.getAll()).hasSize(1);
        assertThat(this.shoppingCart.getAll()).containsOnly(item1);
    }

}
