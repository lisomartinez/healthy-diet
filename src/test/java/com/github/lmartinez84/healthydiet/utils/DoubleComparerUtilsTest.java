package com.github.lmartinez84.healthydiet.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleComparerUtilsTest {
    @Test
    void can_compare_two_double() {
        assertThat(DoubleComparerUtils.isGreaterThanOrEqual(1.0001, 1)).isTrue();
        assertThat(DoubleComparerUtils.isLessThanOrEqual(1, 1.00001)).isTrue();
    }

}