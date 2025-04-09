package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product{
    double basePrice;
    double discount;

    public DiscountedProduct(String nameProduct, double basePrice, double discount, UUID id) throws IllegalArgumentException{
        super(nameProduct, id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100%");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    public double getDiscountedPrice() {
        double price;
        return price = basePrice * (1 - discount / 100.0);
    }

    @Override
    public double getPrice() {
        return getDiscountedPrice();
    }

    @Override
    public String toString() {
        return super.toString() + " со скидкой: " + getDiscountedPrice() + " (скидка " + discount + "%)";
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
