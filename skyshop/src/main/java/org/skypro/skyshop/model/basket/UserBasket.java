package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItems;
    private final double totalBasketPrice;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.totalBasketPrice = calculateTotalBasketPrice(basketItems);
    }

    public double calculateTotalBasketPrice(List<BasketItem> basketItems) {
        return basketItems.stream()
                .mapToDouble(x -> x.getProduct().getProductPrice() * x.getQuantity())
                .sum();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public double getTotalBasketPrice() {
        return totalBasketPrice;
    }
}
