package org.skypro.skyshop.model.basket;

import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket(Map<UUID, Integer> productBasket) {
        this.productBasket = productBasket;
    }

    public void addProduct(UUID uuid) {
        productBasket.merge(uuid, 1, Integer::sum);
    }

    public Map<UUID, Integer> getProductBasket() {
        return Collections.unmodifiableMap(productBasket);
    }
}
