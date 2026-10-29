package edu.course.lab03;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PercentDiscountTest {
    @Test void twentyPercentGivesEighty() { assertEquals(80.0, new PercentDiscount(20).apply(100)); }
    @Test void invalidPercentRejected() { assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101)); }
}
