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
import java.util.Optional;
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
        SimpleProduct tomato3 = new SimpleProduct("Помидор80", 80);
        FixPriceProduct cucumber = new FixPriceProduct("Огурец");
        DiscountedProduct bread = new DiscountedProduct("Хлеб", 30, 5);
        Article tomatoes = new Article("Помидоры", "Помидоры в большинстве случаев красные");
        Article cucumbers = new Article("Огурцы", "Огурцы всега зеленые");
        Article breads = new Article("Хлебы", "Хлебы всега сытные");
        storageProduct.put(tomato3.getId(), tomato3);
        storageProduct.put(cucumber.getId(), cucumber);
        storageProduct.put(bread.getId(), bread);
        storageArticle.put(tomatoes.getId(), tomatoes);
        storageArticle.put(cucumbers.getId(), cucumbers);
        storageArticle.put(breads.getId(), breads);
    }

    public Collection<Searchable> allProductsAndArticle() {
        return Stream.concat(
                storageProduct.values().stream(),
                storageArticle.values().stream()
        ).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(getStorageProduct().get(id));
    }
}
