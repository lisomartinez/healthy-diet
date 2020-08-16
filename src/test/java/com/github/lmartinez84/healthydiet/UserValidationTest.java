package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.exceptions.BirthDateNotInPastUserException;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.exceptions.DiabeticHasNotFavoriteFoodUserException;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.exceptions.HypertensiveHasNotFavoriteFoodUserException;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.exceptions.ShortNameUserException;
import com.github.lmartinez84.healthydiet.domain.user.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UserValidationTest {
    @Test
    public void name_should_not_be_null() {
        assertThatExceptionOfType(NullNameUserException.class).isThrownBy(UserObjectMother::userWithNullFirstName);
    }

    @Test
    public void name_should_not_be_empty() {
        assertThatExceptionOfType(EmptyNameUserException.class).isThrownBy(UserObjectMother::userWithEmptyFirstName);
    }

    @Test
    public void name_should_have_more_than_four_characters() {
        assertThatExceptionOfType(ShortNameUserException.class).isThrownBy(UserObjectMother::userWithShortFirstName);
    }

    @Test
    public void weight_should_not_be_zero() {
        assertThatExceptionOfType(NoWeightUserException.class).isThrownBy(UserObjectMother::userWithZeroWeight);
    }

    @Test
    public void height_should_not_be_zero() {
        assertThatExceptionOfType(NoHeightUserException.class).isThrownBy(UserObjectMother::userWithZeroHeight);
    }

    @Test
    public void birthDate_should_not_be_null() {
        assertThatExceptionOfType(NoBirthDatetUserException.class).isThrownBy(UserObjectMother::userWithoutBirthDate);
    }

    @Test
    public void birthDate_should_be_in_the_past() {
        assertThatExceptionOfType(BirthDateNotInPastUserException.class)
                .isThrownBy(UserObjectMother::userWithoutBirthDateInThePast);
    }

    @Test
    public void routine_should_not_be_in_null() {
        assertThatExceptionOfType(NoRoutineUserException.class)
                .isThrownBy(UserObjectMother::userWithoutRoutine);
    }

    @Test
    public void hypertensive_should_have_at_least_one_favorite_food() {
        assertThatExceptionOfType(HypertensiveHasNotFavoriteFoodUserException.class)
                .isThrownBy(UserObjectMother::hypertensiveUserWithoutFavoriteFood);
    }

    @Test
    public void diabetic_should_have_at_least_one_favorite_food() {
        assertThatExceptionOfType(DiabeticHasNotFavoriteFoodUserException.class)
                .isThrownBy(UserObjectMother::diabeticUserWithoutFavoriteFood);
    }
}
