package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int PRODUCT_PRICE = 100;

    public FixPriceProduct(String productName, UUID id) {
        super(productName, id);
    }

    public FixPriceProduct(String productName) {
        super(productName, UUID.randomUUID());
    }

    @Override
    public int getProductPrice() {
        return PRODUCT_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return super.getProductName() + ": " + getProductPrice();
    }
}
