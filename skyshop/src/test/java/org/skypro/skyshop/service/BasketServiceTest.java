package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;
    @InjectMocks
    private BasketService basketService;

    @Test
    void addProduct_WhenNoSuchProductInStorage_ThenTrowNoSuchProductException() {
        UUID uuid = UUID.randomUUID();
        when(storageService.getProductById(uuid)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(uuid));

        verify(storageService).getProductById(uuid);
    }

    @Test
    void addProduct_WhenSuchProductExist_ThenAddProduct() {
        Product product = new FixPriceProduct("tomatoes");
        when(storageService.getProductById(product.getId())).thenReturn(Optional.of(product));
        doNothing().when(productBasket).addProduct(product.getId());

        basketService.addProduct(product.getId());

        verify(productBasket, times(1)).addProduct(product.getId());
    }

    @Test
    void getUserBasket_WhenNoItemsInBasket_ThenGetEmptyUserBasket() {

        when(productBasket.getAllProductsInBasket()).thenReturn(new HashMap<>());

        UserBasket result = basketService.getUserBasket();

        assertThat(result).isNotNull();
        assertThat(result.getBasketItems()).isEmpty();

        verify(productBasket).getAllProductsInBasket();
    }

    @Test
    void getUserBasket_WhenItemsInBasketExist_ThenGetUserBasket() {
        Map<UUID, Integer> expectedItems = new HashMap<>();
        FixPriceProduct productOne = new FixPriceProduct("grapes", UUID.randomUUID());
        FixPriceProduct productTwo = new FixPriceProduct("cucumber", UUID.randomUUID());

        expectedItems.put(productOne.getId(), 7);
        expectedItems.put(productTwo.getId(), 9);

        when(productBasket.getAllProductsInBasket()).thenReturn(expectedItems);
        when(storageService.getProductById(productOne.getId())).thenReturn(Optional.of(productOne));
        when(storageService.getProductById(productTwo.getId())).thenReturn(Optional.of(productTwo));

        UserBasket result = basketService.getUserBasket();

        assertThat(result).isNotNull();
        assertThat(result.getBasketItems()).extracting(s -> s.getProduct().getId()).
                containsExactlyInAnyOrderElementsOf(expectedItems.keySet());
        assertThat(result.getBasketItems()).extracting(s -> s.getQuantity()).
                containsExactlyInAnyOrderElementsOf(expectedItems.values());
    }
}
