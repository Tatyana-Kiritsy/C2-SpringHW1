package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int productPrice;
    private int discountInPercent;

    public DiscountedProduct(String productName, UUID id, int productPrice, int discountInPercent) {
        super(productName, id);
        if (productPrice <= 0 || discountInPercent < 0 || discountInPercent > 100 ) {
            throw new IllegalArgumentException("Проверьте корректость цены и процента!");
        }
        this.productPrice = productPrice;
        this.discountInPercent = discountInPercent;
    }

    public int getDiscountInPercent() {
        return discountInPercent;
    }

    @Override
    public int getProductPrice() {
        return productPrice - productPrice * discountInPercent / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return super.getProductName() + ": " + getProductPrice() + " (" + discountInPercent + " %)";
    }
}
