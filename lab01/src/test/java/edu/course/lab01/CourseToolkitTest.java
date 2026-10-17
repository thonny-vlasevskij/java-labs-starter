package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);
        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);
        assertFalse(result);
    }

    @Test
    void returnsTrueForZero() {
        boolean result = CourseToolkit.isEven(0);
        assertTrue(result);
    }

    // отрицательное четное число
    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);
        assertTrue(result);
    }

    // Тесты для IsPrime
    @Test
    void returnsFalseForNumberLessThanTwo() {
        assertFalse(CourseToolkit.isPrime(-1));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void returnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void returnsTrueForPrimeNumber() {
        assertTrue(CourseToolkit.isPrime(13));
    }

    @Test
    void returnsFalseForCompositeNumber() {
        assertFalse(CourseToolkit.isPrime(15));
    }

    @Test
    void returnsFalseForSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49));
    }

    // Тесты для IsPalindrome
    @Test
    void returnsTrueForPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void returnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("hello"));
    }

    @Test
    void palindromeIsCaseAndSpaceSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
        assertFalse(CourseToolkit.isPalindrome("a a "));
    }

    @Test
    void returnsTrueForEmptyPalindrome() {
        assertTrue(CourseToolkit.isPalindrome(""));
    }

    @Test
    void throwsForNullPalindromeText() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    // Тесты для average
    @Test
    void calculatesAverage() {
        int[] values = {1, 2, 4};
        double result = CourseToolkit.average(values);
        assertEquals(7.0 / 3.0, result);
    }

    @Test
    void calculatesAverageForNegativeValues() {
        int[] values = {-2, -4, 0};
        double result = CourseToolkit.average(values);
        assertEquals(-2.0, result);
    }

    @Test
    void averageDoesNotChangeInputArray() {
        int[] values = {4, 1, 7};
        int[] original = {4, 1, 7};
        CourseToolkit.average(values);
        assertArrayEquals(original, values);
    }

    @Test
    void throwsForNullAverageArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void throwsForEmptyAverageArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[0]));
    }

    @Test
    void findsMinimumAndMaximum() {
        int[] values = {5, -2, 10, 3};
        assertEquals(-2, CourseToolkit.min(values));
        assertEquals(10, CourseToolkit.max(values));
    }

    @Test
    void minAndMaxDoNotChangeInputArray() {
        int[] values = {5, -2, 10};
        int[] original = {5, -2, 10};
        CourseToolkit.min(values);
        CourseToolkit.max(values);
        assertArrayEquals(original, values);
    }

    @Test
    void minAndMaxRejectEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(new int[0]));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(new int[0]));
    }
}