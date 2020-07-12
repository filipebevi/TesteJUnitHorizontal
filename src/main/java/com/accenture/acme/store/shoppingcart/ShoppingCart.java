package com.accenture.acme.store.shoppingcart;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {
    private Set<Item> items = new HashSet<>();

    public void add(final Item item){
        this.items.add(item);
    }

    public void remove(long productId) {
        this.items.removeIf(i -> i.getProductId() == productId);
    }

    public void clear() {
        this.items.clear();
    }

    public void updateQuantity(long productId, int quantity) {
        this.items.stream()
                .filter(item -> item.getProductId() == productId)
                .forEach(item -> item.setQuantity(quantity));
    }

    public Set<Item> getAll() {
        return Collections.unmodifiableSet(this.items);
    }

    public BigDecimal getSubTotal() {
         return this.items.stream()
                 .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                 .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
