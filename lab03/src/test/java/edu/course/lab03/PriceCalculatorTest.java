package edu.course.lab03;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PriceCalculatorTest {
    @Test void policyCanBeReplaced() {
        assertEquals(100.0, new PriceCalculator(new NoDiscount()).calculate(100));
        assertEquals(80.0, new PriceCalculator(new PercentDiscount(20)).calculate(100));
    }
}
