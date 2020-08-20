package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.user.domain.Routine;
import com.github.lmartinez84.healthydiet.user.domain.User;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.time.LocalDate;

import static com.github.lmartinez84.healthydiet.user.domain.UserBuilder.aUser;
import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class UserTest {

    private final Offset<Double> offset = Offset.offset(0.00000000001);


    @Test
    void very_severely_underweight_user_should_have_BMI_less_than_15() {
        User user = UserObjectMother.createUserWithWeightAndHeight(48.59, 1.8);
        double expectedBMI = 14.996913580246913;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void severely_underweight_should_have_BMI_between_15_and_less_than_16() {
        User user = UserObjectMother.createUserWithWeightAndHeight(51.7, 1.8);
        double expectedBMI = 15.95679012345679;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void underweight_should_have_BMI_between_16_and_eighteen_dot_five_and_eighteen_dot_five() {
        User user = UserObjectMother.createUserWithWeightAndHeight(59.94, 1.8);
        double expectedBMI = 18.499999999999996;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void normal_should_have_BMI_between_eighteen_dot_five_and_25() {
        User user = UserObjectMother.createUserWithWeightAndHeight(80.99, 1.8);
        double expectedBMI = 24.99691358024691;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void overweight_should_have_BMI_between_25_and_30() {
        User user = UserObjectMother.createUserWithWeightAndHeight(97, 1.8);
        double expectedBMI = 29.93827160493827;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void obese_class_1_should_have_BMI_between_30_and_35() {
        User user = UserObjectMother.createUserWithWeightAndHeight(113.3, 1.8);
        double expectedBMI = 34.96913580246913;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void obese_class_2_should_have_BMI_between_35_and_40() {
        User user = UserObjectMother.createUserWithWeightAndHeight(129.6, 1.8);
        double expectedBMI = 39.99999999999999;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void obese_class_4_should_have_BMI_more_than_40() {
        User user = UserObjectMother.createUserWithWeightAndHeight(150, 1.8);
        double expectedBMI = 46.29629629629629;

        assertThat(user.calculateBMI()).isCloseTo(expectedBMI, offset);
    }

    @Test
    void sane_username_are_equals() {
        User user = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();

        User otherUser = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
        assertThat(user).isEqualTo(otherUser);
    }

    @Test
    void different_username_are_not_equals() {
        User user = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jpa")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();

        User otherUser = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
        assertThat(user).isNotEqualTo(otherUser);
    }

    @Test
    void same_username_same_hashcode() {
        User user = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();

        User otherUser = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();

        assertThat(user)
                .isEqualTo(otherUser)
                .hasSameHashCodeAs(otherUser);
    }

    @Test
    void different_username_different_hashcode() {
        User user = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jpa")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();

        User otherUser = aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
        assertThat(user).isNotEqualTo(otherUser);
        assertThat(user.hashCode()).isNotEqualTo(otherUser.hashCode());
    }
}
