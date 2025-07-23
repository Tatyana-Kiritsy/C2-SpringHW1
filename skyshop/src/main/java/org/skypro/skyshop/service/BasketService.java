package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new IllegalArgumentException("Такого продукта в корзине нет!");
        } else {
            productBasket.addProduct(id);
        }
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = productBasket.getAllProductsInBasket()
                .entrySet()
                .stream()
                .map(x -> new BasketItem(storageService.
                        getProductById(x.getKey()).orElseThrow(), x.getValue()))
                .toList();
        return new UserBasket(items);
    }


}
