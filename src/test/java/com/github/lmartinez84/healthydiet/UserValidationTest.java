package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.user.domain.EmptyNameUserException;
import com.github.lmartinez84.healthydiet.user.domain.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Execution(ExecutionMode.CONCURRENT)
class UserValidationTest {
    @Test
    void name_should_not_be_null() {
        assertThatExceptionOfType(NullNameUserException.class).isThrownBy(UserObjectMother::userWithNullFirstName);
    }

    @Test
    void name_should_not_be_empty() {
        assertThatExceptionOfType(EmptyNameUserException.class).isThrownBy(UserObjectMother::userWithEmptyFirstName);
    }

    @Test
    void name_should_have_more_than_four_characters() {
        assertThatExceptionOfType(ShortNameUserException.class).isThrownBy(UserObjectMother::userWithShortFirstName);
    }

    @Test
    void weight_should_not_be_zero() {
        assertThatExceptionOfType(NoWeightUserException.class).isThrownBy(UserObjectMother::userWithZeroWeight);
    }

    @Test
    void height_should_not_be_zero() {
        assertThatExceptionOfType(NoHeightUserException.class).isThrownBy(UserObjectMother::userWithZeroHeight);
    }

    @Test
    void birthDate_should_not_be_null() {
        assertThatExceptionOfType(NoBirthDatetUserException.class).isThrownBy(UserObjectMother::userWithoutBirthDate);
    }

    @Test
    void birthDate_should_be_in_the_past() {
        assertThatExceptionOfType(BirthDateNotInPastUserException.class)
                .isThrownBy(UserObjectMother::userWithoutBirthDateInThePast);
    }

    @Test
    void routine_should_not_be_in_null() {
        assertThatExceptionOfType(NoRoutineUserException.class)
                .isThrownBy(UserObjectMother::userWithoutRoutine);
    }

    @Test
    void hypertensive_should_have_at_least_one_favorite_food() {
        assertThatExceptionOfType(HypertensiveHasNotFavoriteFoodUserException.class)
                .isThrownBy(UserObjectMother::hypertensiveUserWithoutFavoriteFood);
    }

    @Test
    void diabetic_should_have_at_least_one_favorite_food() {
        assertThatExceptionOfType(DiabeticHasNotFavoriteFoodUserException.class)
                .isThrownBy(UserObjectMother::diabeticUserWithoutFavoriteFood);
    }
}
