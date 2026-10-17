package edu.course.lab01;

public final class CourseToolkit {

    private CourseToolkit() {
    }

    // true, если число четное
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    // true, если число простое
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }

        return true;
    }

    // true, если строка читается одинаково слева направо и справа налево
    public static boolean isPalindrome(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left = left + 1;
            right = right - 1;
        }

        return true;
    }

    // среднее арифметическое элементов массива
    public static double average(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be null or empty");
        }

        int sum = 0;
        for (int value : values) {
            sum += value;
        }

        return (double) sum / values.length;
    }

    // Минимальное значение
    public static int min(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be null or empty");
        }

        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < result) {
                result = values[i];
            }
        }

        return result;
    }

    // максимальный элемент
    public static int max(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be null or empty");
        }

        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > result) {
                result = values[i];
            }
        }

        return result;
    }
}