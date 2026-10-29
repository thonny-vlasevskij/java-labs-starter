package edu.course.lab03;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DiscountPolicyTest {
    @Test void noDiscountKeepsPrice() { assertEquals(100.0, new NoDiscount().apply(100.0)); }
    @Test void negativePriceRejected() { assertThrows(IllegalArgumentException.class, () -> new NoDiscount().apply(-1)); }
}
