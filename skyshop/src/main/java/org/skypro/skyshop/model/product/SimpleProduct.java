package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int productPrice;

    public SimpleProduct(String productName, UUID id, int productPrice) {
        super(productName, id);
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Неверно указана цена!");
        }
        this.productPrice = productPrice;
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return super.getProductName() + ": " + getProductPrice();
    }
}
