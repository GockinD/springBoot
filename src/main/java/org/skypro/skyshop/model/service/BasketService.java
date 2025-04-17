package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void addProductInBasketById(UUID uuid) {
        storageService.getProductById(uuid).ifPresentOrElse(product -> productBasket.addProduct(uuid),
                () -> {
                    throw new IllegalArgumentException("Продукт с id " + uuid + " не найден");
                });
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketMap = productBasket.getProductBasket();
        List<BasketItem> basketItemList = basketMap.entrySet().stream()
                .map(map -> new BasketItem(storageService.getProductById(map.getKey()).orElseThrow(), map.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new UserBasket(basketItemList);
    }
}
