package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void add_nonExistentProduct_and_throwingException() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenThrow(new NoSuchProductException());

        assertThrows(NoSuchProductException.class, () -> basketService.addProductInBasketById(id));
    }

    @Test
    void add_existingProduct_callsMethodAddProduct() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.of(new SimpleProduct("мука", 1)));

        basketService.addProductInBasketById(id);

        verify(productBasket).addProduct(id);
    }

    @Test
    void methodGetUserBasket_returnsEmptyBasket_if_ProductBasketEmpty() {
        when(productBasket.getProductBasket()).thenReturn(Collections.emptyMap());
        UserBasket results = basketService.getUserBasket();
        assertTrue(results.getBasketItems().isEmpty());
    }
//Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары.
    @Test
    void methodGetUserBasket_returnsMatchingBucket_if_ProductBasketHasProducts() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        SimpleProduct product1 = new SimpleProduct("продукт 1", 10);
        SimpleProduct product2 = new SimpleProduct("продукт 2", 20);

        when(productBasket.getProductBasket()).thenReturn(Map.of(productId1, 2, productId2, 3));

        when(storageService.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(productId2)).thenReturn(Optional.of(product2));

        UserBasket result = basketService.getUserBasket();

        assertEquals(2, result.getBasketItems().size());
        assertEquals(80.0, result.getTotal());
    }
}
