package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product{
    private static final double PRICE = 49;

    public FixPriceProduct(String nameProduct, UUID id) {
        super(nameProduct, id);
    }

    @Override
    public double getPrice() {
        return PRICE;
    }

    @Override
    public String toString() {
        return super.toString() + " с фиксированной ценой: Фиксированная цена " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public UUID getId() {
        return null;
    }
}
