package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) throws NoSuchProductException {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException("Такого продукта нет!");
        }
        productBasket.addProduct(id);
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
