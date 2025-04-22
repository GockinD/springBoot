package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
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

    public void addProductInBasketById(UUID uuid) {
        if (storageService.getProductById(uuid).isEmpty()) {
            throw new NoSuchProductException();
        }
        productBasket.addProduct(uuid);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = productBasket.getProductBasket()
                .entrySet()
                .stream()
                .map(el -> new BasketItem(storageService.getProductById(el.getKey()).orElseThrow(), el.getValue()))
                .toList();
        return new UserBasket(items);
    }
}
