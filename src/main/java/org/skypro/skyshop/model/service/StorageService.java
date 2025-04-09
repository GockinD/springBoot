package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService(Map<UUID, Product> storageProduct, Map<UUID, Article> storageArticle) {
        this.storageProduct = storageProduct;
        this.storageArticle = storageArticle;
        completionMap();
    }

    public Map<UUID, Product> getStorageProduct() {
        return storageProduct;
    }

    public Map<UUID, Article> getStorageArticle() {
        return storageArticle;
    }

    private void completionMap() {
        SimpleProduct tomato3 = new SimpleProduct("Помидор80", 80, UUID.randomUUID());
        FixPriceProduct cucumber = new FixPriceProduct("Огурец", UUID.randomUUID());
        DiscountedProduct bread = new DiscountedProduct("Хлеб", 30, 5, UUID.randomUUID());
        Article tomatoes = new Article("Помидоры", "Помидоры в большинстве случаев красные", UUID.randomUUID());
        Article cucumbers = new Article("Огурцы", "Огурцы всега зеленые", UUID.randomUUID());
        Article breads = new Article("Хлебы", "Хлебы всега сытные", UUID.randomUUID());
        storageProduct.put(UUID.randomUUID(), tomato3);
        storageProduct.put(UUID.randomUUID(), cucumber);
        storageProduct.put(UUID.randomUUID(), bread);
        storageArticle.put(UUID.randomUUID(), tomatoes);
        storageArticle.put(UUID.randomUUID(), cucumbers);
        storageArticle.put(UUID.randomUUID(), breads);
    }

    public Collection<Searchable> allProductsAndArticle() {
        return Stream.concat(
                storageProduct.values().stream(),
                storageArticle.values().stream()
        ).collect(Collectors.toList());
    }
}
