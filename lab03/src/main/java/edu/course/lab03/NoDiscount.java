package edu.course.lab03;
public class NoDiscount implements DiscountPolicy {
    @Override public double apply(double price) {
        if (price < 0) throw new IllegalArgumentException("price must not be negative");
        return price;
    }
}
