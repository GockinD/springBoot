package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String nameProduct;
    private final UUID id;

    public Product(String nameProduct, UUID id) throws IllegalArgumentException {
        this.id = id;
        if (nameProduct == null || nameProduct.isBlank()) {
            throw new IllegalArgumentException("Имя продукта указано неверно");
        }
        this.nameProduct = nameProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return nameProduct;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return toString();
    }

    public abstract boolean isSpecial();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameProduct);
    }

    @Override
    public UUID getId() {
        return id;
    }
}


