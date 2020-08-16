package com.github.lmartinez84.healthydiet.utils;

public final class DoubleComparerUtils {

    public static final double OFFSET = 0.00000001;

    private DoubleComparerUtils() {
    }

    public static boolean isLessThanOrEqual(double x, double y) {
        return (x - y) <= OFFSET;
    }

    public static boolean isGreaterThanOrEqual(double x, double y) {
        return (x - y) >= OFFSET;
    }
}
