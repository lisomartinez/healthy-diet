package com.github.lmartinez84.healthydiet;

import com.github.lmartinez84.healthydiet.domain.Routine;
import com.github.lmartinez84.healthydiet.domain.user.User;
import com.github.lmartinez84.healthydiet.domain.user.UserBuilder;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.DiabeticDietaryRequirement;
import com.github.lmartinez84.healthydiet.domain.user.dietary_requirement.HypertensiveDietaryRequirement;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.lmartinez84.healthydiet.domain.user.UserBuilder.aUser;

class UserObjectMother {
    static User createUserWithWeightAndHeight(double weight, double height) {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(weight)
                .height(height)
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .build();
    }

    static UserBuilder aRandomUserWith() {
        return aUser()
                .username("jp")
                .firstName("Juan Pablo")
                .lastName("Perez")
                .routine(Routine.ACTIVE)
                .dateOfBirth(LocalDate.of(2001, 9, 11));
    }


    static User userWithNullFirstName() {
        return aUser()
                .firstName(null)
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(180)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    static User userWithEmptyFirstName() {
        return aUser()
                .firstName("")
                .lastName("Perez")
                .username("jp")
                .weight(90)
                .height(180)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    static User userWithZeroWeight() {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(0)
                .height(180)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    static User userWithZeroHeight() {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(30)
                .height(0)
                .dateOfBirth(LocalDate.of(2001, 9, 11))
                .routine(Routine.ACTIVE)
                .build();
    }

    static User userWithoutBirthDate() {
        return aUser()
                .firstName("Juan Pablo")
                .lastName("Perez")
                .username("jp")
                .weight(30)
                .dateOfBirth(null)
                .height(1.8)
                .routine(Routine.ACTIVE)
                .build();
    }

    static User createRandomUser() {
        return UserBuilder.aUser()
                          .firstName("Juan Pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .build();
    }

    static User userWithoutBirthDateInThePast() {
        return UserBuilder.aUser()
                          .firstName("Juan Pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.now())
                          .routine(Routine.ACTIVE)
                          .build();
    }

    static User userWithoutRoutine() {
        return UserBuilder.aUser()
                          .firstName("Juan Pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .build();
    }

    static User userWithShortFirstName() {
        return UserBuilder.aUser()
                          .firstName("Jua")
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .build();
    }

    static User hypertensiveUserWithoutFavoriteFood() {
        return UserBuilder.aUser()
                          .firstName("Juan pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .dietaryRequirements(Set.of(new HypertensiveDietaryRequirement()))
                          .build();
    }

    static User diabeticUserWithoutFavoriteFood() {
        return UserBuilder.aUser()
                          .firstName("Juan pablo" + ThreadLocalRandom.current().nextInt())
                          .lastName("Perez" + ThreadLocalRandom.current().nextInt())
                          .username("jp" + ThreadLocalRandom.current().nextInt())
                          .weight(90)
                          .height(1.8)
                          .dateOfBirth(LocalDate.of(2001, 9, 11))
                          .routine(Routine.ACTIVE)
                          .dietaryRequirements(Set.of(new DiabeticDietaryRequirement()))
                          .build();
    }
}
