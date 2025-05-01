package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String nameProduct;
    private final UUID id;

    public Product(String nameProduct) throws IllegalArgumentException {
        this.id = UUID.randomUUID();
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
        return this.nameProduct;
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

    @JsonProperty
    @Override
    public UUID getId() {
        return id;
    }
}


